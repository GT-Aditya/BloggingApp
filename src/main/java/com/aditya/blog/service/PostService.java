package com.aditya.blog.service;

import java.util.List;

import com.aditya.blog.payload.PostDTO;
import com.aditya.blog.payload.PostResponse;

public interface PostService {

	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

	PostDTO updatePost(PostDTO postDTO, Integer postId);

	void deletePost(Integer postId);

	PostResponse getPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	PostDTO getPostById(Integer postId);

	List<PostDTO> getPostByUser(Integer userId);

	List<PostDTO> getPostByCategory(Integer categoryId);

	List<PostDTO> searchPosts(String keyword);
}
