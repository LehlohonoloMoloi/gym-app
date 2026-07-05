package com.sire.gym.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    RETRIEVE_MEMBER_SUCCESS("Member retrieved successfully"),
    CREATE_MEMBER_SUCCESS("Member created successfully"),
    UPDATE_MEMBER_SUCCESS("Member updated successfully"),
    DELETE_MEMBER_SUCCESS("Member deleted successfully"),
    CREATE_MEMBERSHIP_SUCCESS("Membership created successfully"),
    CANCEL_MEMBERSHIP_SUCCESS("Membership cancelled successfully"),
    RETRIEVE_MEMBERSHIP_SUCCESS("Membership retrieved successfully");

    private final String message;

}
