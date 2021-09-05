package com.github.karthikreddii.models;

/**
 * Plain java model class that contains input and output strings
 */
public class Output {

    private final String input;
    private final String output;

    /**
     * constructor
     * @param input user inputted integer value of 1-3999.
     * @param output Roman number equivalent to the inputted integer value.
     */
    public Output(String input, String output) {
        this.input = input;
        this.output = output;
    }
}
