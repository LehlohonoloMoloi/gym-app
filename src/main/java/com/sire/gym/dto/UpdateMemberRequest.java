package com.sire.gym.dto;

public record UpdateMemberRequest(
        String firstName,

        String lastName,

        String phoneNumber) {}
