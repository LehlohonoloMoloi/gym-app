package com.sire.gym.service;

import com.sire.gym.dto.ApiResponse;
import com.sire.gym.dto.CreateMemberRequest;
import com.sire.gym.dto.MemberResponse;
import com.sire.gym.dto.UpdateMemberRequest;
import org.springframework.data.domain.Page;

public interface MemberService {

    ApiResponse<MemberResponse> createMember(CreateMemberRequest request);
    Page<MemberResponse> getMembers(int page, int size);
    ApiResponse<MemberResponse> getMemberById(Long memberId);
    ApiResponse<MemberResponse> updateMember(Long memberId, UpdateMemberRequest request);
    ApiResponse<Void> deleteMember(Long memberId);

}
