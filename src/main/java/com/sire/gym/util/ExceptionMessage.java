package com.sire.gym.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    MEMBER_NOT_FOUND("Member with ID %d not found"),
    MEMBERSHIP_NOT_FOUND("Membership for member with ID %d not found");

    private final String message;

}
