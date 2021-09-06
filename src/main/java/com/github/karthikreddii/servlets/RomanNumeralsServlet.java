package com.github.karthikreddii.servlets;

import com.github.karthikreddii.models.Output;
import com.github.karthikreddii.services.IntegerToRomanService;
import com.github.karthikreddii.utils.NumberUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.commons.metrics.Counter;
import org.apache.sling.commons.metrics.Meter;
import org.apache.sling.commons.metrics.MetricsService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * This servlet takes input from query parameter query
 * an integer between 1 and 3999 and outputs json response of equivalent roman numeral or a valid error message.
 */
@Component(service = {Servlet.class},
        property = {
            ServletResolverConstants.SLING_SERVLET_PATHS + "=/romannumeral",
            ServletResolverConstants.SLING_SERVLET_METHODS + "=GET"})
public class RomanNumeralsServlet extends SlingSafeMethodsServlet {

    private final Logger logger = LoggerFactory.getLogger(RomanNumeralsServlet.class);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Reference
    private IntegerToRomanService integerToRomanService;

    @Reference
    private MetricsService metricsService;

    private Counter counter;

    private Counter successCounter;

    private Meter meter;

    /**
     * This method will send a json response of input number and Roman number as output <br>
     * if the input string is a number of 1-3999
     * This method will send a relevant text response if the input string is not a valid number.
     * @param request SlingHttpServletRequest
     * @param response SlingHttpServletResponse
     * @throws ServletException
     * @throws IOException
     * @throws NumberFormatException
     */
    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) throws ServletException,
            IOException, NumberFormatException {

        // initialize the metrics
        initialiseMetrics();

        // increment counter after calling api
        counter.increment();

        // meter measures rate of requests per second
        meter.mark();

        String input = request.getParameter("query");

        if (input == null || input.equals("")) {
            writeValidationError(response, "Your input is empty, enter a number between 1 and 3999");
            return;
        }

        if (!NumberUtil.isNumber(input)) {
            writeValidationError(response,
                    "Your input is not a valid number, enter a number between 1 and 3999");
            return;
        }

        int inputNumber = Integer.parseInt(input);
        if (inputNumber > 0 && inputNumber < 4000) {
            String output = integerToRomanService.intToRoman(inputNumber);
            Output res = new Output(input, output);
            response.getWriter().write(gson.toJson(res));
            successCounter.increment();
        } else {
            writeValidationError(response, "Please input a number between 1 and 3999");
            logger.debug("Input number {} is out of range", inputNumber);
        }
    }

    /**
     * Initialize the metric objects
     */
    private void initialiseMetrics() {
        counter = metricsService.counter("romanNumeral-pageHits");
        successCounter = metricsService.counter("romanNumeral-intInRange");
        meter = metricsService.meter("romanNumeral-MeterInfo");
    }

    /**
     * Takes the error message and sets that to the response
     * @param response SlingHttpServletResponse
     * @param message error message
     * @throws IOException
     */
    private void writeValidationError(SlingHttpServletResponse response, String message) throws IOException {
        response.setContentType("text/plain");
        response.getWriter().write(message);
        response.getWriter().close();
    }

}
