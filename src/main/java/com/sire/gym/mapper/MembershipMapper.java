package com.sire.gym.mapper;

import com.sire.gym.dto.MembershipResponse;
import com.sire.gym.model.Membership;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MembershipMapper {

    @Mapping(target = "memberId", source = "member.id")
    MembershipResponse toMembershipResponse(Membership membership);

    List<MembershipResponse> toMembershipResponseList(List<Membership> memberships);

}
