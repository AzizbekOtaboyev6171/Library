package com.management.library.service;

import com.management.library.dto.member.MemberCreateDTO;
import com.management.library.dto.member.MemberDTO;
import com.management.library.dto.member.MemberUpdateDTO;
import org.springframework.data.domain.Page;

public interface MemberService {
    MemberDTO createMember(MemberCreateDTO memberCreateDTO);

    MemberDTO updateMember(Long id, MemberUpdateDTO memberUpdateDTO);

    MemberDTO getMemberById(Long id);

    void deleteMemberById(Long id);

    Page<MemberDTO> getMembers(int page, int size);
}