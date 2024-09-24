package com.sparta.schedule.dto.comment;

import com.sparta.schedule.dto.user.UserDto;
import lombok.Getter;

@Getter
public class CommentSaveResponseDto {
    private final Long id;
    private final String contents;
    private final UserDto user;

    public CommentSaveResponseDto(Long id, String contents, UserDto user) {
        this.id = id;
        this.contents = contents;
        this.user =user;
    }

}
