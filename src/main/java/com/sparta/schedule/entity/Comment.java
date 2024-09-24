package com.sparta.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends TimeStamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    public Comment(String contents, User user, Todo todo) {
        this.contents = contents;
        this.user = user;
        this.todo = todo;
    }

    public void update(String contents) {
        this.contents = contents;
    }
}
