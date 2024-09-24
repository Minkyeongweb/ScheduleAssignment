package com.sparta.schedule.controller;

import com.sparta.schedule.dto.user.UserDetailResponseDto;
import com.sparta.schedule.dto.user.UserSaveRequestDto;
import com.sparta.schedule.dto.user.UserSaveResponseDto;
import com.sparta.schedule.dto.user.UserSimpleResponseDto;
import com.sparta.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserSaveResponseDto> saveUser(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return ResponseEntity.ok(userService.saveUser(userSaveRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<UserSimpleResponseDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailResponseDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
