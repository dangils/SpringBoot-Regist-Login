package com.koreait.springuser.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    USER("ROLE_USER", "일반사용자"),
    MANAGER("ROLE_MANAGER", "매니저"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
