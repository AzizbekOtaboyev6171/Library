package com.management.library.controller;

import com.management.library.dto.member.MemberCreateDTO;
import com.management.library.dto.member.MemberDTO;
import com.management.library.dto.member.MemberUpdateDTO;
import com.management.library.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create_member")
    @Operation(summary = "Create a new member", tags = {"Member"})
    public ResponseEntity<MemberDTO> createMember(@Valid @RequestBody MemberCreateDTO memberCreateDTO) {
        return ResponseEntity.ok(memberService.createMember(memberCreateDTO));
    }

    @PutMapping("/update_member/{id}")
    @Operation(summary = "Update a member", tags = {"Member"})
    public ResponseEntity<MemberDTO> updateMember(@PathVariable(name = "id") Long id, @Valid @RequestBody MemberUpdateDTO memberUpdateDTO) {
        return ResponseEntity.ok(memberService.updateMember(id, memberUpdateDTO));
    }

    @GetMapping("/get_member/{id}")
    @Operation(summary = "Get a member", tags = {"Member"})
    public ResponseEntity<MemberDTO> getMember(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @DeleteMapping("/delete_member/{id}")
    @Operation(summary = "Delete a member", tags = {"Member"})
    public ResponseEntity<String> deleteMember(@PathVariable(name = "id") Long id) {
        memberService.deleteMemberById(id);
        return ResponseEntity.ok("Member deleted successfully!");
    }

    @GetMapping("/get_members")
    @Operation(summary = "Get all members", tags = {"Member"})
    public ResponseEntity<Page<MemberDTO>> getMembers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(memberService.getMembers(page, size));
    }
}