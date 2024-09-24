package com.sparta.schedule.service;

import com.sparta.schedule.dto.manager.ManagerDetailResponseDto;
import com.sparta.schedule.dto.manager.ManagerSaveRequestDto;
import com.sparta.schedule.dto.manager.ManagerSaveResponseDto;
import com.sparta.schedule.dto.user.UserDto;
import com.sparta.schedule.entity.Manager;
import com.sparta.schedule.entity.Todo;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.ManagerRepository;
import com.sparta.schedule.repository.TodoRepository;
import com.sparta.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public ManagerSaveResponseDto savaManager(Long todoId, ManagerSaveRequestDto managerSaveRequestDto) {
        //실제 있는 일정을 가져오고 등록하려고 하는 유저가 일정을 만든 사람인지 확인하면서 가져온다.
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()-> new NullPointerException("할 일이 없습니다."));
        User user = userRepository.findById(managerSaveRequestDto.getTodoUserId())
                .orElseThrow(()->new NullPointerException("사용자가 없습니다."));

        if (user.getId() != null && ObjectUtils.nullSafeEquals(user.getId(), todo.getUser().getId())) {
            throw new NullPointerException("등록하려고 하는 유저가 일정을 만든 유저가 아닙니다.");
        }
        User manager = userRepository.findById(managerSaveRequestDto.getManagerUserId())
                .orElseThrow(()->new NullPointerException("사용자가 없습니다."));

        Manager newManager = new Manager(manager, todo);
        Manager savedManager = managerRepository.save(newManager);

        return new ManagerSaveResponseDto(savedManager.getId());
    }

    public List<ManagerDetailResponseDto> getManagers(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()-> new NullPointerException("할 일이 없습니다."));

        List<Manager> managerList = managerRepository.findByTodoId(todo.getId());

        List<ManagerDetailResponseDto> dtoList = new ArrayList<>();
        for (Manager manager : managerList) {
            User user = manager.getUser();
            dtoList.add(new ManagerDetailResponseDto(
                            manager.getId(),
                            new UserDto(user.getId(), user.getUsername(),user.getEmail())
                    ));
        }
        return dtoList;
    }
}
