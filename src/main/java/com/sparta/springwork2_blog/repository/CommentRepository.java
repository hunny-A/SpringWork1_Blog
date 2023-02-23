package com.sparta.springwork2_blog.repository;

import com.sparta.springwork2_blog.entity.Blog;
import com.sparta.springwork2_blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Comment> findById(Long id);
    List<Comment> findAllByBlogOrderByCreatedAtAsc(Blog blog);
}
