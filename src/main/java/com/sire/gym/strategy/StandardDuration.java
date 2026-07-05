package com.sire.gym.strategy;

import com.sire.gym.common.MembershipDuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("standard")
public class StandardDuration implements DurationStrategy {

    @Override
    public LocalDate getEndDate() {
        return LocalDate.now().plusMonths(MembershipDuration.STANDARD.getDuration());
    }

}
