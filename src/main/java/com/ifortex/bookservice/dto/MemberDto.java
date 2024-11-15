package com.ifortex.bookservice.dto;

import java.time.LocalDateTime;

public record MemberDto(
        String name,
        LocalDateTime membershipDate
) {
}
