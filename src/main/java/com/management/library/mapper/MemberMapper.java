package com.management.library.mapper;

import com.management.library.dto.member.MemberDTO;
import com.management.library.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper extends EntityMapper<MemberDTO, Member> {
}