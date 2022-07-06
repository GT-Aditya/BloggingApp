package com.aditya.blog.service;

import com.aditya.blog.payload.CommentDTO;

public interface CommentService{

	CommentDTO createComment(CommentDTO commentDTO, Integer postId);
	void deleteComment(Integer commentId);
}
