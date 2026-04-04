package com.gyana.Gyana_Social.Response;

public class ErrorResponse {
    private String message;
    private String errorCode;
    private long timestamp;
    private String path;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, String errorCode, String path) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = System.currentTimeMillis();
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", timestamp=" + timestamp +
                ", path='" + path + '\'' +
                '}';
    }
}
