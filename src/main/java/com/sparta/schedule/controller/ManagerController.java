package com.sparta.schedule.controller;

import com.sparta.schedule.dto.manager.ManagerDetailResponseDto;
import com.sparta.schedule.dto.manager.ManagerSaveRequestDto;
import com.sparta.schedule.dto.manager.ManagerSaveResponseDto;
import com.sparta.schedule.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/todos")
@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/{todoId}/managers")
    public ResponseEntity<ManagerSaveResponseDto> saveManager(@PathVariable Long todoId,@RequestBody ManagerSaveRequestDto managerSaveRequestDto) {
        return ResponseEntity.ok(managerService.savaManager(todoId, managerSaveRequestDto));
    }

    @GetMapping("/{todoId}/managers")
    public ResponseEntity<List<ManagerDetailResponseDto>> getMembers(@PathVariable Long todoId) {
        return ResponseEntity.ok(managerService.getManagers(todoId));
    }
}
