package com.sire.gym.service;

import com.sire.gym.dto.ApiResponse;
import com.sire.gym.dto.CreateMemberRequest;

public interface MemberService {

    ApiResponse<Void> createMember(CreateMemberRequest request);
    ApiResponse<Void> getMembers();
    ApiResponse<Void> getMemberById(Long memberId);
    ApiResponse<Void> updateMember(Long memberId, CreateMemberRequest request);
    ApiResponse<Void> deleteMember(Long memberId);

}
