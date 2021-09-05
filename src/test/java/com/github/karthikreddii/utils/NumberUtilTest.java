package com.github.karthikreddii.utils;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * NumberUtil Coverage and functional tests
 */
public class NumberUtilTest {

    /**
     * Tests the case if the input is Not a number
     */
    @Test
    public void numberTest() {
        assertFalse(NumberUtil.isNumber("karthik"));
    }

    /**
     * Tests the case if the input is a valid number
     */
    @Test
    public void textTest() {
        assertTrue(NumberUtil.isNumber("77"));
    }

    /**
     * Tests the case if the input is a special character
     */
    @Test
    public void testSpecialChar() {
        assertFalse(NumberUtil.isNumber("!@@@@@@*"));
    }
}