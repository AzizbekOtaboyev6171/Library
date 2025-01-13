package com.management.library.mapper;

import com.management.library.dto.member.MemberDTO;
import com.management.library.entity.Member;
import com.management.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MemberMapper extends EntityMapper<MemberDTO, Member> {
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "lastModifiedBy.id", target = "lastModifiedBy")
    MemberDTO toDTO(Member member);

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "memberMapIdToUser")
    @Mapping(source = "lastModifiedBy", target = "lastModifiedBy", qualifiedByName = "memberMapIdToUser")
    Member toEntity(MemberDTO memberDTO);

    @Named("memberMapIdToUser")
    default User mapIdToUser(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}