package com.sire.gym.service;

import com.sire.gym.dto.ApiResponse;
import com.sire.gym.dto.CreateMembershipRequest;
import com.sire.gym.dto.MembershipResponse;
import com.sire.gym.model.MembershipStatus;
import org.springframework.data.domain.Page;

public interface MembershipService {

    ApiResponse<MembershipResponse> createMembership(Long memberId, CreateMembershipRequest request);

    ApiResponse<MembershipResponse> retrieveMembership(Long memberId);

    ApiResponse<MembershipResponse> cancelMembership(Long memberId);

    Page<MembershipResponse> getAllMemberships(MembershipStatus status, int page, int size);

}
