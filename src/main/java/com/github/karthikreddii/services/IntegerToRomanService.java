package com.github.karthikreddii.services;

/**
 * This is an OSGi service to provide a method to convert integer to Roman Numeral String
 */
public interface IntegerToRomanService {

    /**
     * This method will return a roman number for the string provided.
     * The input number should be 1-3999. If the number is above or below will throw an ArrayOutOfBoundException
     * @param num takes integer as a string
     * @return Roman number as string
     */
    String intToRoman(int num) throws ArrayIndexOutOfBoundsException, NumberFormatException ;
}
