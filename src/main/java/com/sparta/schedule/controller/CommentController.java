package com.sparta.schedule.controller;

import com.sparta.schedule.dto.comment.*;
import com.sparta.schedule.service.CommentService;
import com.sparta.schedule.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/todos")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final TodoService todoService;

    @PostMapping("/{todoId}/comments")
    public ResponseEntity<CommentSaveResponseDto> saveComment(@PathVariable Long todoId, @RequestBody CommentSaveRequestDto commentSaveRequestDto) {
        return ResponseEntity.ok(commentService.saveComment(todoId, commentSaveRequestDto));
    }

    @GetMapping("/{todoId}/comments")
    public ResponseEntity<List<CommentDetailResponseDto>> getComments(@PathVariable Long todoId) {
        return ResponseEntity.ok(commentService.getComments(todoId));
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDetailResponseDto> getComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentUpdateResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        return ResponseEntity.ok(commentService.updateComment(commentId, commentUpdateRequestDto));
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
