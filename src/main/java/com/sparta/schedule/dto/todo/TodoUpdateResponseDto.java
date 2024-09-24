package com.sparta.schedule.dto.todo;

import lombok.Getter;

@Getter
public class TodoUpdateResponseDto {
    private final Long id;

    public TodoUpdateResponseDto(Long id) {
        this.id = id;
    }
}
