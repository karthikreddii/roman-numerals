package com.github.karthikreddii.services.impl;

import com.github.karthikreddii.services.IntegerToRomanService;
import org.osgi.service.component.annotations.Component;

/**
 * This osgi service provides method to convert a integer number to Roman Number string.
 * For more information on Roman Numbers refer this wikipedia page https://www.britannica.com/topic/Roman-numeral
 */
@Component(service = IntegerToRomanService.class, immediate = true)
public class IntegerToRomanServiceImpl implements IntegerToRomanService {

    /**
     * This method will return a roman number for the string provided.
     * The input number should be 1-3999. If the number is above or below will throw an ArrayOutOfBoundException
     * @param num takes integer as a string
     * @return Roman number as string
     */
    @Override
    public String intToRoman(int num) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] M = {"", "M", "MM", "MMM"};

        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
}
