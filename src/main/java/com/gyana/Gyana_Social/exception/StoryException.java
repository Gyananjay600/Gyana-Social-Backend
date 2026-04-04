package com.gyana.Gyana_Social.exception;

public class StoryException extends Exception {
    private static final long serialVersionUID = 1L;

    public StoryException(String message) {
        super(message);
    }

    public StoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
