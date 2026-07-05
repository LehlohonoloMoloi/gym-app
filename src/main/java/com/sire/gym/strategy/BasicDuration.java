package com.sire.gym.strategy;

import com.sire.gym.common.MembershipDuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("basic")
public class BasicDuration implements DurationStrategy {

    @Override
    public LocalDate getEndDate() {
        return LocalDate.now().plusMonths(MembershipDuration.BASIC.getDuration()); // Basic membership lasts for 1 month
    }

}
