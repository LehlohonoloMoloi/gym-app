package com.sire.gym.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    SUCCESS("SUCCESSFUL"),
    FAILURE("FAILED");

    private final String label;

}
