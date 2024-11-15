package com.ifortex.bookservice.controller;

import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.dto.MemberDto;
import com.ifortex.bookservice.service.CustomMemberService;
import com.ifortex.bookservice.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/members")
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final CustomMemberService customMemberService;

    @GetMapping("old")
    public MemberDto getOldMember() {
        return customMemberService.getWithTheOldestRomanceBook();
    }

    @GetMapping("readless")
    public List<MemberDto> getReadlessMembers() {
        return customMemberService.getMembersWhoHasntReadAnyBook();
    }

    @GetMapping("amateur")
    public Member findMember() {
        return memberService.findMember();
    }

    @GetMapping
    public List<Member> findMembers() {
        return memberService.findMembers();
    }
}
