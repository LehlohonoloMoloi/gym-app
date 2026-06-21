package com.sire.gym.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MembershipDuration {

    BASIC(1),
    STANDARD(3),
    PREMIUM(12);

    private final Integer duration;

}
