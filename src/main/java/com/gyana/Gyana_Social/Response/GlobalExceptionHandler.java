package com.gyana.Gyana_Social.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.gyana.Gyana_Social.exception.JwtException;
import com.gyana.Gyana_Social.exception.MessageException;
import com.gyana.Gyana_Social.exception.PostException;
import com.gyana.Gyana_Social.exception.StoryException;
import com.gyana.Gyana_Social.exception.UserException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle UserException
     */
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(
            UserException ex, WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "USER_ERROR",
                request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle PostException
     */
    @ExceptionHandler(PostException.class)
    public ResponseEntity<ErrorResponse> handlePostException(
            PostException ex, WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "POST_ERROR",
                request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle MessageException
     */
    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ErrorResponse> handleMessageException(
            MessageException ex, WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "MESSAGE_ERROR",
                request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle StoryException
     */
    @ExceptionHandler(StoryException.class)
    public ResponseEntity<ErrorResponse> handleStoryException(
            StoryException ex, WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "STORY_ERROR",
                request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle JwtException
     */
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(
            JwtException ex, WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "JWT_ERROR",
                request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle NullPointerException
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(
            NullPointerException ex, WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
                "A required value is missing or null",
                "NULL_POINTER_ERROR",
                request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle IllegalArgumentException
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "INVALID_ARGUMENT",
                request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle generic Exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
                "An unexpected error occurred. Please try again later.",
                "INTERNAL_SERVER_ERROR",
                request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
