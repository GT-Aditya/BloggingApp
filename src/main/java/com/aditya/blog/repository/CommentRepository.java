package com.aditya.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aditya.blog.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
