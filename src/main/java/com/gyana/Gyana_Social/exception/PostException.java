package com.gyana.Gyana_Social.exception;

public class PostException extends Exception {
    private static final long serialVersionUID = 1L;

    public PostException(String message) {
        super(message);
    }

    public PostException(String message, Throwable cause) {
        super(message, cause);
    }
}
