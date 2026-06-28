package com.sire.gym.service.impl;

import com.sire.gym.dto.ApiResponse;
import com.sire.gym.dto.CreateMemberRequest;
import com.sire.gym.dto.MemberResponse;
import com.sire.gym.dto.UpdateMemberRequest;
import com.sire.gym.exception.MemberExistsException;
import com.sire.gym.exception.MemberNotFoundException;
import com.sire.gym.mapper.MemberMapper;
import com.sire.gym.model.Member;
import com.sire.gym.repository.MemberRepository;
import com.sire.gym.service.MemberService;
import com.sire.gym.util.ResponseMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.sire.gym.util.ResponseUtils.generateSuccessResponse;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public ApiResponse<MemberResponse> createMember(final CreateMemberRequest request) {
        if (memberExists(request.email())) {
            throw new MemberExistsException(String.format("Member with email %s already exists", request.email()));
        }

        Member member = memberMapper.toMember(request);
        Member savedMember = memberRepository.save(member);
        MemberResponse response = memberMapper.toMemberResponse(savedMember);
        return generateSuccessResponse(response, ResponseMessage.CREATE_MEMBER_SUCCESS.getMessage());
    }

    @Override
    public Page<MemberResponse> getMembers(final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Member> memberPage = memberRepository.findAll(pageable);

        // Map the Page<Member> to Page<MemberResponse> using the memberMapper
        return memberPage.map(memberMapper::toMemberResponse);
    }

    @Override
    public ApiResponse<MemberResponse> getMemberById(final String email) {
        Member member = getMember(email);
        MemberResponse memberResponse = memberMapper.toMemberResponse(member);
        return generateSuccessResponse(memberResponse, ResponseMessage.RETRIEVE_MEMBER_SUCCESS.getMessage());
    }

    @Override
    @Transactional
    public ApiResponse<MemberResponse> updateMember(final UpdateMemberRequest request) {
        String memberEmail = request.email();
        if (!memberExists(memberEmail)) {
            throw new MemberNotFoundException(String.format("Member with email %s not found", memberEmail));
        }

        Member existingMember = getMember(memberEmail);
        memberMapper.updateMemberFromRequest(request, existingMember);
        Member updatedMember = memberRepository.save(existingMember);
        MemberResponse response = memberMapper.toMemberResponse(updatedMember);
        return generateSuccessResponse(response, ResponseMessage.UPDATE_MEMBER_SUCCESS.getMessage());
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteMember(final String email) {
        if (!memberExists(email)) {
            throw new MemberNotFoundException(String.format("Member with email %s not found", email));
        }

        memberRepository.deleteByEmail(email);
        return generateSuccessResponse(ResponseMessage.DELETE_MEMBER_SUCCESS.getMessage());
    }

    private Member getMember(final String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException(String.format("Member with email %s not found", email)));
    }

    private boolean memberExists(final String email) {
        return memberRepository.existsByEmail(email);
    }

}
