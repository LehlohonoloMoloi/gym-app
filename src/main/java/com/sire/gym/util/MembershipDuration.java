package com.sire.gym.util;

import lombok.Getter;

@Getter
public enum MembershipDuration {

    BASIC(1),
    STANDARD(3),
    PREMIUM(12);

    private final Integer duration;

    MembershipDuration(Integer duration) {
        this.duration = duration;
    }

}
