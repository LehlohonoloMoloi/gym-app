package com.sire.gym.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateMemberRequest(
        String firstName,

        String lastName,

        String phoneNumber,

        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email) {}
