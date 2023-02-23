package com.sparta.springwork2_blog.entity;

import com.sparta.springwork2_blog.dto.request.BlogRequestDto;
import com.sparta.springwork2_blog.dto.request.CommentRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{
    /* 댓글 번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* 댓글 내용 */
    @Column(nullable = false)
    private String comments;

    /* 댓글이 달리는 게시물 번호  */
    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    /* 게시물 다는 작성자 */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Comment(CommentRequestDto requestDto, User user, Blog blog) {
        this.comments = requestDto.getComments();
        this.user = user;
        this.blog = blog;
    }

    public void update(CommentRequestDto requestDto, User user) {
        this.comments = requestDto.getComments();
        this.user = user;
    }
}
