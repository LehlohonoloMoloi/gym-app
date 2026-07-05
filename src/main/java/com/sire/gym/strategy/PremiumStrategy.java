package com.sire.gym.strategy;

import com.sire.gym.common.MembershipDuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("premium")
public class PremiumStrategy implements DurationStrategy {

    @Override
    public LocalDate getEndDate() {
        return LocalDate.now().plusMonths(MembershipDuration.PREMIUM.getDuration());
    }

}
