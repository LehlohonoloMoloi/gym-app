package com.sire.gym.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    RETRIEVE_MEMBER_SUCCESS("Member retrieved successfully"),
    CREATE_MEMBER_SUCCESS("Member created successfully"),
    UPDATE_MEMBER_SUCCESS("Member updated successfully"),
    DELETE_MEMBER_SUCCESS("Member deleted successfully");

    private final String message;

}
