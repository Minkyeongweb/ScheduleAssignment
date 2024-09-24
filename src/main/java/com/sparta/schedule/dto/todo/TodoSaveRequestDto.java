package com.sparta.schedule.dto.todo;

import lombok.Getter;

@Getter
public class TodoSaveRequestDto {
    private String title;
    private String contents;
    private Long userId;

    }
