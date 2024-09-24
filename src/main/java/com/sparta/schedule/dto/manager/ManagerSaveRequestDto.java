package com.sparta.schedule.dto.manager;

import lombok.Getter;

@Getter
public class ManagerSaveRequestDto {

    private Long todoUserId;
    private Long managerUserId;
    private Long todoId;

}
