package com.sparta.schedule.dto.todo;

import com.sparta.schedule.dto.user.UserDto;
import lombok.Getter;

@Getter
public class TodoSaveResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final UserDto user;

    public TodoSaveResponseDto(Long id, String title, String contents, UserDto user) {
        this.id =id;
        this.title = title;
        this.contents = contents;
        this.user = user;
    }
}
