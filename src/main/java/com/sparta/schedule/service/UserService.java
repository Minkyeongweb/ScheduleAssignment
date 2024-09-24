package com.sparta.schedule.service;

import com.sparta.schedule.dto.user.UserDetailResponseDto;
import com.sparta.schedule.dto.user.UserSaveRequestDto;
import com.sparta.schedule.dto.user.UserSaveResponseDto;
import com.sparta.schedule.dto.user.UserSimpleResponseDto;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserSaveResponseDto saveUser(UserSaveRequestDto userSaveRequestDto) {
        if (userRepository.existsByEmail(userSaveRequestDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User newUser = new User(
                userSaveRequestDto.getUsername(),
                userSaveRequestDto.getEmail()
        );
        User savedUser = userRepository.save(newUser);

        return new UserSaveResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail()
        );
    }

    public List<UserSimpleResponseDto> getUsers() {
        List<User> userList = userRepository.findAll();

        List<UserSimpleResponseDto> dtoList = new ArrayList<>();
        for (User user : userList) {
            dtoList.add(new UserSimpleResponseDto(
                    user.getId(),
                    user.getEmail()
            ));
        }
        return dtoList;
    }

    public UserDetailResponseDto getUser(Long userId) {
      User user = userRepository.findById(userId).orElseThrow(()->new NullPointerException("사용자가 없습니다."));
      return new UserDetailResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NullPointerException("사용자가 없습니다.");
        }
        userRepository.deleteById(userId);
    }
}
