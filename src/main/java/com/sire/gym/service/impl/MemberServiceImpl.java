package com.sire.gym.service.impl;

import com.sire.gym.dto.ApiResponse;
import com.sire.gym.dto.CreateMemberRequest;
import com.sire.gym.repository.MemberRepository;
import com.sire.gym.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public ApiResponse<Void> createMember(CreateMemberRequest request) {
        return null;
    }

    @Override
    public ApiResponse<Void> getMembers() {
        return null;
    }

    @Override
    public ApiResponse<Void> getMemberById(Long memberId) {
        return null;
    }

    @Override
    public ApiResponse<Void> updateMember(Long memberId, CreateMemberRequest request) {
        return null;
    }

    @Override
    public ApiResponse<Void> deleteMember(Long memberId) {
        return null;
    }
}
