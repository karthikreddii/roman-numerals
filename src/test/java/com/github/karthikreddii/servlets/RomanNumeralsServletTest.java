package com.github.karthikreddii.servlets;

import com.github.karthikreddii.services.IntegerToRomanService;

import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * RomanNumeralsServlet Coverage and Functional tests
 */
public class RomanNumeralsServletTest {

    RomanNumeralsServlet romanNumeralsServlet = new RomanNumeralsServlet();

    IntegerToRomanService integerToRomanService = mock(IntegerToRomanService.class);

    //Setup mock SlingHttpServletRequest and SlingHttpServletResponse
    MockSlingHttpServletRequest request = mock(MockSlingHttpServletRequest.class);
    MockSlingHttpServletResponse response = mock(MockSlingHttpServletResponse.class);

    StringWriter responseStringWriter = new StringWriter();
    PrintWriter responsePrintWriter = new PrintWriter(responseStringWriter);

    /**
     * This is a test case if the input is valid number that in between 1-3999
     * @throws ServletException
     * @throws IOException
     * @throws NumberFormatException
     */
    @Test
    public void doGetTestRight() throws ServletException, IOException, NumberFormatException {

        String expectedResponse = "{\n  \"input\": \"77\",\n  \"output\": \"LXXVII\"\n}";

        Whitebox.setInternalState(romanNumeralsServlet, "integerToRomanService", integerToRomanService);

        when(request.getParameter("query")).thenReturn("77");
        when(response.getWriter()).thenReturn(responsePrintWriter);
        when(response.getStatus()).thenReturn(200);
        when(integerToRomanService.intToRoman(Integer.parseInt(request.getParameter("query"))))
                .thenReturn("LXXVII");

        romanNumeralsServlet.doGet(request, response);
        assertEquals("77", request.getParameter("query"));
        assertEquals(expectedResponse, responseStringWriter.toString());
        assertEquals(200, response.getStatus());
    }

    /**
     * This is a test case if the input is Null
     * @throws ServletException
     * @throws IOException
     * @throws NumberFormatException
     */
    @Test
    public void doGetTestNull() throws ServletException, IOException, NumberFormatException {
        String expectedResponse = "Your input is empty, enter a number between 1 and 3999";

        when(request.getParameter("query")).thenReturn(null);
        when(response.getWriter()).thenReturn(responsePrintWriter);

        romanNumeralsServlet.doGet(request, response);
        assertNull(request.getParameter("query"));
        assertEquals(expectedResponse, responseStringWriter.toString());
    }

    /**
     * This is a test case if the number is out of the range
     * @throws ServletException
     * @throws IOException
     * @throws NumberFormatException
     */
    @Test
    public void doGetTestRange() throws ServletException, IOException, NumberFormatException {
        String expectedResponse = "Please input a number between 1 and 3999";

        when(request.getParameter("query")).thenReturn("5000");
        when(response.getWriter()).thenReturn(responsePrintWriter);

        romanNumeralsServlet.doGet(request, response);
        assertEquals("5000", request.getParameter("query"));
        assertEquals(expectedResponse, responseStringWriter.toString());
    }

    /**
     * This is a test case if the input is a string instead of a integer
     * @throws ServletException
     * @throws IOException
     * @throws NumberFormatException
     */
    @Test
    public void doGetTestText() throws ServletException, IOException, NumberFormatException {
        String expectedResponse = "Your input is not a valid number, enter a number between 1 and 3999";

        when(request.getParameter("query")).thenReturn("karthik");
        when(response.getWriter()).thenReturn(responsePrintWriter);

        romanNumeralsServlet.doGet(request, response);
        assertEquals("karthik", request.getParameter("query"));
        assertEquals(expectedResponse, responseStringWriter.toString());
    }

}
