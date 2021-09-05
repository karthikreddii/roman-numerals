package com.github.karthikreddii.services.impl;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * IntegerToRomanService Coverage and functional tests
 */
public class IntegerToRomanServiceImplTest {

    IntegerToRomanServiceImpl integerToRomanServiceImpl = new IntegerToRomanServiceImpl();

    /**
     * Tests the case if the input number is with in the range
     */
    @Test
    public void intToRomanRight() {
        assertEquals("LXXVII", integerToRomanServiceImpl.intToRoman(77));
    }

    /**
     * Tests the case if the input number is out of the range
     * @throws ArrayIndexOutOfBoundsException
     */
    @Test
    public void intToRomanOutOfRange() throws ArrayIndexOutOfBoundsException {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            integerToRomanServiceImpl.intToRoman(4000);
        });
    }

    /**
     * Tests the case if the input is a invalid number
     * @throws NumberFormatException
     */
    @Test
    public void intToRomanNumberFormat() throws NumberFormatException {
        assertThrows(NumberFormatException.class, () -> {
            integerToRomanServiceImpl.intToRoman(Integer.parseInt("karthik"));
        });
    }
}