package com.sire.gym.exception;

public class MembershipExistsException extends RuntimeException {
    public MembershipExistsException(String message) {
        super(message);
    }
}
