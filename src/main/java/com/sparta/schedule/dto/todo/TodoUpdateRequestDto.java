package com.sparta.schedule.dto.todo;

import lombok.Getter;

@Getter
public class TodoUpdateRequestDto {
    private String title;
    private String contents;
    private String username;

    public TodoUpdateRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
