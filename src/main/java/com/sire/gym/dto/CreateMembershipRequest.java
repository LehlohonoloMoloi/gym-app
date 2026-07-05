package com.sire.gym.dto;

import com.sire.gym.model.MembershipType;
import jakarta.validation.constraints.NotNull;

public record CreateMembershipRequest(
        @NotNull(message = "Membership type is required")
        // @Todo: Add validation for membership type (e.g., check if it's a valid enum value) -> BASIC|STANDARD|PREMIUM
        MembershipType type) {}
