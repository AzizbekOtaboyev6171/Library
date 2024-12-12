package com.management.library.service.impl;

import com.management.library.configuration.ApplicationAuditAware;
import com.management.library.dto.member.MemberCreateDTO;
import com.management.library.dto.member.MemberDTO;
import com.management.library.dto.member.MemberUpdateDTO;
import com.management.library.entity.Member;
import com.management.library.exceptions.ResourceAlreadyExistsException;
import com.management.library.exceptions.ResourceNotFoundException;
import com.management.library.mapper.MemberMapper;
import com.management.library.repository.MemberRepository;
import com.management.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final ApplicationAuditAware applicationAuditAware;

    @Override
    public MemberDTO createMember(MemberCreateDTO memberCreateDTO) {
        log.info("Creating member with email: {} and phone: {}", memberCreateDTO.email(), memberCreateDTO.phone());
        if (memberRepository.existsByEmailEqualsIgnoreCase(memberCreateDTO.email())) {
            throw new ResourceAlreadyExistsException("Member with email " + memberCreateDTO.email() + " already exists");
        }
        if (memberRepository.existsByPhone(memberCreateDTO.phone())) {
            throw new ResourceAlreadyExistsException("Member with phone " + memberCreateDTO.phone() + " already exists");
        }
        Member member = new Member();
        member.setName(memberCreateDTO.name());
        member.setEmail(memberCreateDTO.email());
        member.setPhone(memberCreateDTO.phone());
        member.setMembershipDate(memberCreateDTO.membershipDate());
        Member saved = memberRepository.save(member);
        log.info("Member with id: {} created successfully!", saved.getId());
        return memberMapper.toDTO(saved);
    }

    @Override
    public MemberDTO updateMember(Long id, MemberUpdateDTO memberUpdateDTO) {
        log.info("Updating member with id: {}", id);
        Long currentUserId = applicationAuditAware.getCurrentAuditor().orElseThrow();
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member with id " + id + " not found"));
        if (!member.getCreatedBy().equals(currentUserId)) {
            throw new AccessDeniedException("Member with id " + currentUserId + " can not be updated");
        }
        if (memberRepository.existsByEmailEqualsIgnoreCaseAndIdNot(memberUpdateDTO.email(), id)) {
            throw new ResourceAlreadyExistsException("Member with email " + memberUpdateDTO.email() + " already exists");
        }
        if (memberRepository.existsByPhoneAndIdNot(memberUpdateDTO.phone(), id)) {
            throw new ResourceAlreadyExistsException("Member with phone " + memberUpdateDTO.phone() + " already exists");
        }
        member.setName(memberUpdateDTO.name());
        member.setEmail(memberUpdateDTO.email());
        member.setPhone(memberUpdateDTO.phone());
        member.setMembershipDate(memberUpdateDTO.membershipDate());
        Member saved = memberRepository.save(member);
        log.info("Member with id: {} updated successfully!", id);
        return memberMapper.toDTO(saved);
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        log.info("Getting member with id: {}", id);
        return memberRepository.findById(id)
                .map(memberMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Member with id " + id + " not found"));
    }

    @Override
    public void deleteMemberById(Long id) {
        log.info("Deleting member with id: {}", id);
        Long currentUserId = applicationAuditAware.getCurrentAuditor().orElseThrow();
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member with id " + id + " not found"));
        if (!member.getCreatedBy().equals(currentUserId)) {
            throw new AccessDeniedException("Member with id " + currentUserId + " can not be deleted");
        }
        memberRepository.deleteById(id);
        log.info("Member with id: {} deleted successfully!", id);
    }

    @Override
    public Page<MemberDTO> getMembers(int page, int size) {
        log.info("Getting all members");
        return memberRepository.findAll(PageRequest.of(page, size))
                .map(memberMapper::toDTO);
    }
}