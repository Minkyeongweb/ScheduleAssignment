package com.sparta.schedule.dto.user;

import lombok.Getter;

@Getter
public class UserDetailResponseDto {
    private final Long id;
    private final String username;
    private final String email;

    public UserDetailResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
