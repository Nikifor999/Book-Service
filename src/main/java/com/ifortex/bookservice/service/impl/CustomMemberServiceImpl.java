package com.ifortex.bookservice.service.impl;

import com.ifortex.bookservice.dto.MemberDto;
import com.ifortex.bookservice.repository.MemberRepository;
import com.ifortex.bookservice.service.CustomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomMemberServiceImpl implements CustomMemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberDto getWithTheOldestRomanceBook() {
        return memberRepository.findWithTheOldestRomanceBook();
    }

    @Override
    public List<MemberDto> getMembersWhoHasntReadAnyBook() {
        return memberRepository.findMembersWhoHasntReadANyBook();
    }
}
