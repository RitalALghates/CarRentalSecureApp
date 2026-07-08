package com.cccy225.carrental.security;

public final class SecureErrorHandler {
    private SecureErrorHandler() {
    }

    public static String toSafeMessage(Exception exception) {
        if (exception instanceof IllegalArgumentException) {
            return "Input error: " + exception.getMessage();
        }
        return "An unexpected error occurred. Please try again using valid input.";
    }
}
