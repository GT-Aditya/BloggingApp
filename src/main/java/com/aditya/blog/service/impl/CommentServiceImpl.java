package com.aditya.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.blog.entities.Comment;
import com.aditya.blog.entities.Post;
import com.aditya.blog.exception.ResourceNotFoundException;
import com.aditya.blog.payload.CommentDTO;
import com.aditya.blog.payload.PostDTO;
import com.aditya.blog.repository.CommentRepository;
import com.aditya.blog.service.CommentService;
import com.aditya.blog.service.PostService;


@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		PostDTO post = postService.getPostById(postId);
		Comment comment = modelMapper.map(commentDTO, Comment.class);
		comment.setPost(modelMapper.map(post, Post.class));
		Comment res = commentRepo.save(comment);
		return modelMapper.map(res, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
		commentRepo.delete(comment);
	}
	
	public CommentDTO getCommentById(Integer commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
		return modelMapper.map(comment, CommentDTO.class);
	}

}
