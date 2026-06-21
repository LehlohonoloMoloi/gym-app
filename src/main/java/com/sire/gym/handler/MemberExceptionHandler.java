package com.sire.gym.handler;

import com.sire.gym.common.Status;
import com.sire.gym.controller.MemberController;
import com.sire.gym.dto.ApiResponse;
import com.sire.gym.exception.MemberExistsException;
import com.sire.gym.exception.MemberNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {MemberController.class})
public class MemberExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public ApiResponse<Void> handleMemberNotFoundException(MemberNotFoundException ex) {
        return buildErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(MemberExistsException.class)
    public ApiResponse<Void> handleMemberExistsException(MemberExistsException ex) {
        return buildErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");
        return buildErrorResponse(message);
    }

    private ApiResponse<Void> buildErrorResponse(String message) {
        return ApiResponse.of(Status.FAILURE, message);
    }

}
