package com.sparta.schedule.service;

import com.sparta.schedule.dto.comment.*;
import com.sparta.schedule.dto.user.UserDto;
import com.sparta.schedule.entity.Todo;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.TodoRepository;
import com.sparta.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentSaveResponseDto saveComment(Long todoId, CommentSaveRequestDto commentSaveRequestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()->new NullPointerException("할 일이 없습니다."));

        User user = userRepository.findById(commentSaveRequestDto.getId())
                .orElseThrow(()-> new NullPointerException("할 일이 없습니다."));

        Comment newComment = new Comment(
                commentSaveRequestDto.getContents(),
                user,
                todo
                );

        Comment savedComment = commentRepository.save(newComment);

        return new CommentSaveResponseDto(
                savedComment.getId(),
                savedComment.getContents(),
                new UserDto(user.getId(),user.getUsername(), user.getEmail())
        );
    }

    public List<CommentDetailResponseDto> getComments(Long todoId) {
        List<Comment> commentList = commentRepository.findByTodoId(todoId);

        List<CommentDetailResponseDto> dtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            User user = comment.getUser();
            CommentDetailResponseDto dto = new CommentDetailResponseDto(
                    comment.getId(),
                    comment.getContents(),
                    new UserDto(user.getId(),user.getUsername(), user.getEmail())
            );
            dtoList.add(dto);
        }
        return dtoList;
    }
    public CommentDetailResponseDto getComment(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new NullPointerException("댓글이 없습니다."));
        User user = comment.getUser();
        return new CommentDetailResponseDto(
                comment.getId(),
                comment.getContents(),
                new UserDto(user.getId(),user.getUsername(), user.getEmail())
        );
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new NullPointerException("댓글이 없습니다."));

        comment.update(commentUpdateRequestDto.getContents());
        return new CommentUpdateResponseDto(comment.getId(), comment.getContents());
    }
    @Transactional
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new NullPointerException("댓글이 없습니다.");
        }
        commentRepository.deleteById(commentId);
    }
}
