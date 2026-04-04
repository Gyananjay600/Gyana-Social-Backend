package com.gyana.Gyana_Social.exception;

public class JwtException extends Exception {
    private static final long serialVersionUID = 1L;

    public JwtException(String message) {
        super(message);
    }

    public JwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
