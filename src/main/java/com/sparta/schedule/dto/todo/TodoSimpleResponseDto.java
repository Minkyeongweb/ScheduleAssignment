package com.sparta.schedule.dto.todo;

import lombok.Getter;

@Getter
public class TodoSimpleResponseDto {
    private final String title;

    public TodoSimpleResponseDto(String title) {
        this.title = title;
    }
}
