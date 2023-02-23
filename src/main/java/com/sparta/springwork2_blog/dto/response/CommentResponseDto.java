package com.sparta.springwork2_blog.dto.response;

import com.sparta.springwork2_blog.entity.Comment;
import com.sparta.springwork2_blog.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String comments;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.comments = comment.getComments();
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

}
