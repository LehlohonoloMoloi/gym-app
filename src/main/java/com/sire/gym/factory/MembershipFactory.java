package com.sire.gym.factory;

import com.sire.gym.model.Membership;
import com.sire.gym.model.MembershipType;
import com.sire.gym.strategy.DurationStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.sire.gym.model.MembershipType.BASIC;
import static com.sire.gym.model.MembershipType.PREMIUM;
import static com.sire.gym.model.MembershipType.STANDARD;

@Component
public class MembershipFactory {

    private final DurationStrategy basicDurationStrategy;
    private final DurationStrategy standardDurationStrategy;
    private final DurationStrategy premiumDurationStrategy;

    public MembershipFactory(
            @Qualifier("basic") DurationStrategy basicDurationStrategy,
            @Qualifier("standard") DurationStrategy standardDurationStrategy,
            @Qualifier("premium") DurationStrategy premiumDurationStrategy) {
        this.basicDurationStrategy = basicDurationStrategy;
        this.standardDurationStrategy = standardDurationStrategy;
        this.premiumDurationStrategy = premiumDurationStrategy;
    }

    public Membership createMembership(MembershipType type) {
        return switch (type) {
            case BASIC      -> new Membership(BASIC, basicDurationStrategy);
            case STANDARD   -> new Membership(STANDARD, standardDurationStrategy);
            case PREMIUM    -> new Membership(PREMIUM, premiumDurationStrategy);
        };
    }

}
