package com.sire.gym.mapper;

import com.sire.gym.dto.CreateMemberRequest;
import com.sire.gym.dto.MemberResponse;
import com.sire.gym.dto.UpdateMemberRequest;
import com.sire.gym.model.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member toMember(CreateMemberRequest request);

    MemberResponse toMemberResponse(Member member);

    List<MemberResponse> toMemberResponseList(List<Member> members);

    default Member updateMemberFromRequest(UpdateMemberRequest request, Member existingMember) {
        if (request.firstName() != null) {
            existingMember.setFirstName(request.firstName());
        }
        if (request.phoneNumber() != null) {
            existingMember.setPhoneNumber(request.phoneNumber());
        }
        if (request.lastName() != null) {
            existingMember.setLastName(request.lastName());
        }
        return existingMember;
    }
}
