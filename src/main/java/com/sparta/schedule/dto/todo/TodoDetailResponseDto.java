package com.sparta.schedule.dto.todo;

import com.sparta.schedule.dto.user.UserDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoDetailResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final UserDto user;
    private final int commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public TodoDetailResponseDto(Long id, String title, String contents, UserDto user, int commentCount, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user = user;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


}
