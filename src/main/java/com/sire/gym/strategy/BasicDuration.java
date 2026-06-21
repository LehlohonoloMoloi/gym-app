package com.sire.gym.strategy;

import com.sire.gym.common.MembershipDuration;

import java.time.LocalDate;

public class BasicDuration implements DurationStrategy {

    @Override
    public LocalDate getEndDate() {
        return LocalDate.now().plusMonths(MembershipDuration.BASIC.getDuration()); // Basic membership lasts for 1 month
    }

}
