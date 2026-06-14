package com.sire.gym.dto;

public record MembershipResponse(
        Long id,
        String type,
        String status,
        String startDate,
        String endDate,
        Long memberId) {}
