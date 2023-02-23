package com.sparta.springwork2_blog.service;

import com.sparta.springwork2_blog.dto.request.BlogRequestDto;
import com.sparta.springwork2_blog.dto.request.CommentRequestDto;
import com.sparta.springwork2_blog.dto.response.BlogResponseDto;
import com.sparta.springwork2_blog.dto.response.CommentResponseDto;
import com.sparta.springwork2_blog.dto.response.MegResponseDto;
import com.sparta.springwork2_blog.entity.Blog;
import com.sparta.springwork2_blog.entity.Comment;
import com.sparta.springwork2_blog.entity.User;
import com.sparta.springwork2_blog.entity.UserRoleEnum;
import com.sparta.springwork2_blog.jwt.JwtUtil;
import com.sparta.springwork2_blog.repository.BlogRepository;
import com.sparta.springwork2_blog.repository.CommentRepository;
import com.sparta.springwork2_blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;
    private final JwtUtil jwtUtil;


    /* 댓글 작성 */
    @Transactional
    public CommentResponseDto createComment(Long id, User user, CommentRequestDto commentRequestDto) {

        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        if(user.getRole() != null){
            Comment comment = commentRepository.save(new Comment(commentRequestDto,user, blog));
            return new CommentResponseDto(comment);

        }
        throw new IllegalArgumentException("권한이 없습니다.");
    }

    /* 댓글 수정 */
    @Transactional
    public CommentResponseDto update(Long id, User user, CommentRequestDto requestDto, HttpServletRequest request) {

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        if(comment.getUser().getUsername().equals(user.getUsername()) || user.getRole() == UserRoleEnum.ADMIN){
            comment.update(requestDto,user);
        } else {
            throw new IllegalArgumentException("실사용자가 아닙니다.");
        }

        return new CommentResponseDto(comment);
    }

    @Transactional
    public ResponseEntity<MegResponseDto> delete(Long id, User user, HttpServletRequest request) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        if(comment.getUser().getUsername().equals(user.getUsername()) || user.getRole() == UserRoleEnum.ADMIN){
            blogRepository.delete(comment.getBlog());
        } else {
            return ResponseEntity.ok()
                    .body(MegResponseDto.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .msg("실 사용자가 아닙니다.")
                            .build());
        }
        return ResponseEntity.ok()
                .body(MegResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("댓글 삭제 성공")
                        .build());
    }
}
