package com.ifortex.bookservice.repository;

import com.ifortex.bookservice.dto.MemberDto;

import java.util.List;

public interface CustomMemberRepository {

    MemberDto findWithTheOldestRomanceBook();

    List<MemberDto> findMembersWhoHasntReadANyBook();
}
