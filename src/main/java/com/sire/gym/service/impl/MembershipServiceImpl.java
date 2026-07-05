package com.sire.gym.service.impl;

import com.sire.gym.dto.ApiResponse;
import com.sire.gym.dto.CreateMembershipRequest;
import com.sire.gym.dto.MembershipResponse;
import com.sire.gym.exception.MemberNotFoundException;
import com.sire.gym.exception.MembershipExistsException;
import com.sire.gym.factory.MembershipFactory;
import com.sire.gym.mapper.MembershipMapper;
import com.sire.gym.model.Member;
import com.sire.gym.model.Membership;
import com.sire.gym.model.MembershipStatus;
import com.sire.gym.repository.MemberRepository;
import com.sire.gym.repository.MembershipRepository;
import com.sire.gym.service.MembershipService;
import com.sire.gym.util.ExceptionMessage;
import com.sire.gym.util.ResponseMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.sire.gym.util.ResponseUtils.generateSuccessResponse;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;
    private final MemberRepository memberRepository;
    private final MembershipFactory membershipFactory;
    private final MembershipMapper membershipMapper;

    @Override
    public ApiResponse<MembershipResponse> createMembership(final Long memberId, final CreateMembershipRequest request) {
        Member member = getMember(memberId);

        if (activeMembershipExists(memberId)) {
            throw new MembershipExistsException(String.format("Membership for member with ID %d already exists", memberId));
        }

        Membership membership = membershipFactory.createMembership(request.type());
        membership.setMember(member);
        Membership savedMembership = membershipRepository.save(membership);
        MembershipResponse response = membershipMapper.toMembershipResponse(savedMembership);
        return generateSuccessResponse(response, ResponseMessage.CREATE_MEMBERSHIP_SUCCESS.getMessage());
    }

    @Override
    @Transactional
    public ApiResponse<MembershipResponse> retrieveMembership(final Long memberId) {
        if (memberNotExists(memberId)) {
            throw new MemberNotFoundException(String.format(ExceptionMessage.MEMBER_NOT_FOUND.getMessage(), memberId));
        }

        Membership membership = getUpdatedMembershipInformation(memberId);
        MembershipResponse response = membershipMapper.toMembershipResponse(membership);
        return generateSuccessResponse(response, ResponseMessage.RETRIEVE_MEMBERSHIP_SUCCESS.getMessage());
    }

    @Override
    @Transactional
    public ApiResponse<MembershipResponse> cancelMembership(final Long memberId) {
        if (memberNotExists(memberId)) {
            throw new MemberNotFoundException(String.format(ExceptionMessage.MEMBER_NOT_FOUND.getMessage(), memberId));
        } else if (!activeMembershipExists(memberId)) {
            throw new MemberNotFoundException(String.format(ExceptionMessage.MEMBERSHIP_NOT_FOUND.getMessage(), memberId));
        }

        Membership membership = getMembership(memberId);
        membership.setStatus(MembershipStatus.CANCELLED);
        Membership updatedMembership = membershipRepository.save(membership);
        MembershipResponse response = membershipMapper.toMembershipResponse(updatedMembership);
        return generateSuccessResponse(response, ResponseMessage.CANCEL_MEMBERSHIP_SUCCESS.getMessage());
    }

    @Override
    public Page<MembershipResponse> getAllMemberships(final MembershipStatus status, final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        if (status == null) {
            return membershipRepository.findAll(pageable)
                    .map(membershipMapper::toMembershipResponse);
        }

        return membershipRepository.findByStatus(status, pageable)
                .map(membershipMapper::toMembershipResponse);
    }

    private Member getMember(final Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(String.format(ExceptionMessage.MEMBER_NOT_FOUND.getMessage(), memberId)));
    }

    private Membership getUpdatedMembershipInformation(final Long memberId) {
        Membership membership = getMembership(memberId);

        // Check if the membership has expired and update its status if necessary
        if (LocalDate.now().isAfter(membership.getEndDate())) {
            return setMembershipToExpired(membership);
        }

        // If the membership is still active, return it as is
        return membership;
    }

    private Membership getMembership(final Long memberId) {
        return membershipRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException(String.format(ExceptionMessage.MEMBERSHIP_NOT_FOUND.getMessage(), memberId)));
    }

    private Membership setMembershipToExpired(final Membership membership) {
        membership.setStatus(MembershipStatus.EXPIRED);
        return membershipRepository.save(membership);
    }

    private boolean memberNotExists(final Long memberId) {
        return !memberRepository.existsById(memberId);
    }

    private boolean activeMembershipExists(final Long memberId) {
        return membershipRepository.activeMembershipExists(memberId);
    }

}
