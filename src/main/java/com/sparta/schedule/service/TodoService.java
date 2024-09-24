package com.sparta.schedule.service;

import com.sparta.schedule.dto.todo.*;
import com.sparta.schedule.dto.user.UserDto;
import com.sparta.schedule.entity.Todo;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.TodoRepository;
import com.sparta.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public TodoSaveResponseDto saveTodo(TodoSaveRequestDto todoSaveRequestDto) {
        User user = userRepository.findById(todoSaveRequestDto.getUserId()).orElseThrow(()->new NullPointerException("사용자가 없습니다."));
        Todo newTodo = new Todo(
                todoSaveRequestDto.getTitle(),
                todoSaveRequestDto.getContents(),
                user
        );
        Todo savedTodo = todoRepository.save(newTodo);

        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getTitle(),
                savedTodo.getContents(),
                new UserDto(user.getId(), user.getUsername(), user.getEmail())
        );
    }

    @Transactional
    public TodoDetailResponseDto getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("할 일이 없습니다."));

        return new TodoDetailResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getContents(),
                new UserDto(todo.getUser().getId(), todo.getUser().getUsername(), todo.getUser().getEmail()),
                todo.getComments().size(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    @Transactional
    public TodoUpdateResponseDto updateTodo(Long todoId, TodoUpdateRequestDto todoUpdateRequestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()->new NullPointerException("할 일이 없습니다."));

        todo.update(
                todoUpdateRequestDto.getTitle(),
                todoUpdateRequestDto.getContents()
        );
        return new TodoUpdateResponseDto(todo.getId());
    }

    @Transactional
    public Page<TodoDetailResponseDto> getTodos(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Todo> todos = todoRepository.findAllByOrderByModifiedAtDesc(pageable);
        //for문은 return이 없다. 그래서 map을 쓴다.
        return todos.map(todo -> {
            User user = todo.getUser();
           return new TodoDetailResponseDto(
                    todo.getId(),
                    todo.getTitle(),
                    todo.getContents(),
                    new UserDto(user.getId(), user.getUsername(), user.getEmail()),
                    todo.getComments().size(),
                    todo.getCreatedAt(),
                    todo.getModifiedAt()
            );
        });
    }

    public void deleteTodo(Long todoId) {

    }
}
