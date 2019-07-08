package com.movie.popcornapp.extensions;

/**
 * @author george.radu on 2019-07-08.
 */
public class StringUtils {
    private static String currentAmount = "";
    private static int cursorPosition;

    /**
     * Checks whether a String is empty.
     *
     * @param string a string to check
     * @return true if the string is null or empty, false otherwise
     */
    public static boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }
}
