package com.sparta.springwork2_blog.controller;

import com.sparta.springwork2_blog.dto.request.BlogRequestDto;
import com.sparta.springwork2_blog.dto.request.CommentRequestDto;
import com.sparta.springwork2_blog.dto.response.BlogResponseDto;
import com.sparta.springwork2_blog.dto.response.CommentResponseDto;
import com.sparta.springwork2_blog.dto.response.MegResponseDto;
import com.sparta.springwork2_blog.security.UserDetailsImpl;
import com.sparta.springwork2_blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blogs/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(id, userDetails.getUser(), commentRequestDto);
    }

    @PutMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return commentService.update(id, userDetails.getUser(), requestDto,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MegResponseDto> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request){
        return commentService.delete(id, userDetails.getUser(), request);
    }

}
