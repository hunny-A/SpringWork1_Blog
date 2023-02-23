package com.sparta.springwork2_blog.dto.request;

import com.sparta.springwork2_blog.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private Long id;
    private String comments;
    private String username;
    private User user;
}
