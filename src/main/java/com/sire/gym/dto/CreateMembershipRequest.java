package com.sire.gym.dto;

import com.sire.gym.model.MembershipType;
import jakarta.validation.constraints.NotNull;

public record CreateMembershipRequest(
        @NotNull(message = "Member ID is required")
        Long memberId,

        @NotNull(message = "Membership type is required")
        MembershipType type) {}
