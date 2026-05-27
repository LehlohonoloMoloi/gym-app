package com.sire.gym.dto;

public record MemberResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String joinedDate) {
}

