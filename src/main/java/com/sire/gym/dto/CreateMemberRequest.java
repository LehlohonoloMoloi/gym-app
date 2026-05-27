package com.sire.gym.dto;

public record CreateMemberRequest(String firstName, String lastName, String email, String phoneNumber) {
}
