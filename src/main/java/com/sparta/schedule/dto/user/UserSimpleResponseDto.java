package com.sparta.schedule.dto.user;

import lombok.Getter;

@Getter
public class UserSimpleResponseDto {
    private final Long id;
    private final String username;

    public UserSimpleResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
