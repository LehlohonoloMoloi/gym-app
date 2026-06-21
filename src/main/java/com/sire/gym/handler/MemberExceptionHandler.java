package com.sire.gym.handler;

import com.sire.gym.common.Status;
import com.sire.gym.dto.ApiResponse;
import com.sire.gym.exception.MemberExistsException;
import com.sire.gym.exception.MemberNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public ApiResponse<Void> handleMemberNotFoundException(MemberNotFoundException ex) {
        return buildErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(MemberExistsException.class)
    public ApiResponse<Void> handleMemberExistsException(MemberExistsException ex) {
        return buildErrorResponse(ex.getMessage());
    }

    private ApiResponse<Void> buildErrorResponse(String message) {
        return ApiResponse.<Void>builder()
                .status(Status.FAILURE)
                .message(message)
                .build();
    }

}
