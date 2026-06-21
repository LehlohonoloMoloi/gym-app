package com.sire.gym.dto;

import com.sire.gym.common.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    private Status status;
    private T data;
    private String message;

    public static <T> ApiResponse<T> of(Status status, T data, String message) {
        return new ApiResponse<>(status, data, message);
    }

    public static <T> ApiResponse<T> of(Status status, String message) {
        return ApiResponse.<T>builder()
                .status(status)
                .message(message)
                .build();
    }

}
