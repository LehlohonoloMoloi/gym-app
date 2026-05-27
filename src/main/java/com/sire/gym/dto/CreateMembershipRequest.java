package com.sire.gym.dto;

import com.sire.gym.model.MembershipType;

public record CreateMembershipRequest(Long memberId, MembershipType type) {
}
