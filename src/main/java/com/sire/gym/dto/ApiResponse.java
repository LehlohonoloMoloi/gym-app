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
}
