package com.ifortex.bookservice.repository.impl;

import com.ifortex.bookservice.dto.MemberDto;
import com.ifortex.bookservice.repository.CustomMemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public MemberDto findWithTheOldestRomanceBook() {
        String sql = """
                SELECT mem.id AS memberId, mem.name AS memberName, mem.membership_date AS membershipDate
                FROM members AS mem
                INNER JOIN member_books AS mb ON mem.id = mb.member_id
                INNER JOIN books AS b ON mb.book_id = b.id
                WHERE 'Romance' = ANY(b.genre)
                ORDER BY b.publication_date ASC, mem.membership_date DESC
                FETCH FIRST 1 ROW ONLY
                """;

        List<Object[]> result = entityManager.createNativeQuery(sql).getResultList();

        if (result.isEmpty()) {
            return null;
        }

        Object[] row = result.get(0);
        Timestamp timestamp = (Timestamp) row[2];
        MemberDto member = new MemberDto((String) row[1], timestamp.toLocalDateTime());

        return member;
    }

    @Override
    public List<MemberDto> findMembersWhoHasntReadANyBook() {
        String sql = """
                SELECT *
                FROM book_service.members AS mem
                LEFT JOIN book_service.member_books AS mb ON mem.id = mb.member_id
                WHERE mb.book_id IS NULL
                AND EXTRACT(YEAR FROM membership_date) = 2023;
                """;
        List<Object[]> result = entityManager.createNativeQuery(sql).getResultList();

        if (result.isEmpty()) {
            return null;
        }

        List<MemberDto> members = new ArrayList<>();
        for (Object[] row : result) {
            Timestamp timestamp = (Timestamp) row[2];
            MemberDto member = new MemberDto((String) row[1], timestamp.toLocalDateTime());
            members.add(member);
        }

        return members;
    }

}
