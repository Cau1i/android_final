package com.example.myapp.util;

public class StringUtils {
    public static boolean isEmpty(String string) {
        if (string.equals("") || string.length() <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
