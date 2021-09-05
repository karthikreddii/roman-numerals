package com.github.karthikreddii.utils;

import java.util.regex.Pattern;

/**
 * This is a util class to perform operations on numbers
 */
public final class NumberUtil {

    private NumberUtil() {
        //Util classes should not be instantiated
    }

    /**
     * This method will return true if the input string is a positive number
     * @param str input string
     * @return true if the input string is a positive number
     */
    public static boolean isNumber(String str) {
        String regex = "[0-9]+[\\.]?[0-9]*";
        return Pattern.matches(regex, str);
    }
}
