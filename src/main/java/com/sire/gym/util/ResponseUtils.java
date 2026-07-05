package com.sire.gym.util;

import com.sire.gym.common.Status;
import com.sire.gym.dto.ApiResponse;
import com.sire.gym.dto.MemberResponse;
import com.sire.gym.dto.MembershipResponse;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ResponseUtils {

    public static ApiResponse<MemberResponse> generateSuccessResponse(MemberResponse memberResponse, String message) {
        return ApiResponse.<MemberResponse>builder()
                .status(Status.SUCCESS)
                .data(memberResponse)
                .message(message)
                .build();
    }

    public static ApiResponse<Void> generateSuccessResponse(String message) {
        return ApiResponse.<Void>builder()
                .status(Status.SUCCESS)
                .message(message)
                .build();
    }

    public static ApiResponse<MembershipResponse> generateSuccessResponse(MembershipResponse membershipResponse, String message) {
        return ApiResponse.<MembershipResponse>builder()
                .status(Status.SUCCESS)
                .data(membershipResponse)
                .message(message)
                .build();
    }

}
