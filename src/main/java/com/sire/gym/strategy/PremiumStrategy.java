package com.sire.gym.strategy;

import com.sire.gym.common.MembershipDuration;

import java.time.LocalDate;

public class PremiumStrategy implements DurationStrategy {

    @Override
    public LocalDate getEndDate() {
        return LocalDate.now().plusMonths(MembershipDuration.PREMIUM.getDuration());
    }

}
