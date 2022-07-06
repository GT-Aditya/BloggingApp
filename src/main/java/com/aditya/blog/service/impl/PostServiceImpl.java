package com.aditya.blog.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aditya.blog.entities.Category;
import com.aditya.blog.entities.Post;
import com.aditya.blog.entities.User;
import com.aditya.blog.exception.ResourceNotFoundException;
import com.aditya.blog.payload.PostDTO;
import com.aditya.blog.payload.PostResponse;
import com.aditya.blog.repository.PostRepository;
import com.aditya.blog.service.CategoryService;
import com.aditya.blog.service.PostService;
import com.aditya.blog.service.UserService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

		User user = modelMapper.map(userService.getUserById(userId), User.class);
		Category category = modelMapper.map(categoryService.getCategoryById(categoryId), Category.class);

		Post post = modelMapper.map(postDTO, Post.class);
		post.setUser(user);
		post.setCategory(category);
		post.setCreateAt(LocalDate.now());
		post.setImageName("Default.jpeg");

		Post response = postRepository.save(post);

		return modelMapper.map(response, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post_id", postId));
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setImageName(postDTO.getImageName());
		Post response = postRepository.save(post);
		return modelMapper.map(response, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post_id", postId));
		postRepository.delete(post);
	}

	@Override
	public PostResponse getPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pages = postRepository.findAll(pageable);
		List<PostDTO> posts = pages.getContent().stream().map((post) -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		PostResponse response = new PostResponse();
		response.setData(posts);
		response.setPageNumber(pages.getNumber());
		response.setPageSize(pages.getSize());
		response.setTotalElements((int) pages.getTotalElements());
		response.setTotalPages(pages.getTotalPages());
		response.setLastPage(pages.isLast());
		return response;
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post_id", postId));
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		User user = modelMapper.map(userService.getUserById(userId), User.class);
		List<Post> posts = postRepository.findByUser(user);
		List<PostDTO> postDTOs = posts.stream().map((post) -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		return postDTOs;
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		Category category = modelMapper.map(categoryService.getCategoryById(categoryId), Category.class);
		List<Post> posts = postRepository.findByCategory(category);
		List<PostDTO> postDTOs = posts.stream().map((post) -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		List<Post> posts = postRepository.searchByKey("%" + keyword + "%");
		List<PostDTO> postDTOs = posts.stream().map((post) -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return postDTOs;
	}

}
