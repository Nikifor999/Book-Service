package com.ifortex.bookservice.service;

import com.ifortex.bookservice.dto.MemberDto;

import java.util.List;

public interface CustomMemberService {
    MemberDto getWithTheOldestRomanceBook();

    List<MemberDto> getMembersWhoHasntReadAnyBook();
}
