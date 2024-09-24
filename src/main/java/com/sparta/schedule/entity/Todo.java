package com.sparta.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Todo extends TimeStamped{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//원투매니는 자기자신을 mappedby에 넣어준다.
    @OneToMany(mappedBy = "todo")
    private List<Comment> comments  = new ArrayList<>();

    public Todo(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
