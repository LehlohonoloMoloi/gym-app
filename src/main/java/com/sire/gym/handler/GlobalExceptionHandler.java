package com.sire.gym.handler;

import com.sire.gym.common.Status;
import com.sire.gym.controller.MemberController;
import com.sire.gym.dto.ApiResponse;
import com.sire.gym.exception.MemberExistsException;
import com.sire.gym.exception.MemberNotFoundException;
import com.sire.gym.exception.MembershipExistsException;
import com.sire.gym.exception.MembershipNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleMemberNotFoundException(MemberNotFoundException ex) {
        ApiResponse<Void> response = buildErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");
        ApiResponse<Void> response = buildErrorResponse(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<Void>> handleMissingParameterException(MissingServletRequestParameterException ex) {
        String message = "Missing required parameter: " + ex.getParameterName();
        ApiResponse<Void> response = buildErrorResponse(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleConstraintViolationException(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .findFirst()
                .orElse("Constraint violation");
        ApiResponse<Void> response = buildErrorResponse(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemberExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleMemberExistsException(MemberExistsException ex) {
        ApiResponse<Void> response = buildErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MembershipExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleMembershipExistsException(MembershipExistsException ex) {
        ApiResponse<Void> response = buildErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MembershipNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleMembershipNotFoundException(MembershipNotFoundException ex) {
        ApiResponse<Void> response = buildErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String message = "Malformed JSON request: " + ex.getMostSpecificCause().getMessage();
        ApiResponse<Void> response = buildErrorResponse(message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private ApiResponse<Void> buildErrorResponse(String message) {
        return ApiResponse.of(Status.FAILURE, message);
    }

}
