package com.codewithchhotu.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithchhotu.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
