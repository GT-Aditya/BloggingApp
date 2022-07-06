package com.aditya.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aditya.blog.entities.Category;
import com.aditya.blog.exception.ResourceNotFoundException;
import com.aditya.blog.payload.CategoryDTO;
import com.aditya.blog.repository.CategoryRepository;
import com.aditya.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = modelMapper.map(categoryDTO, Category.class);
		Category response = categoryRepo.save(category);
		return  modelMapper.map(response, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer cId) {
		Category category = categoryRepo.findById(cId).orElseThrow(()->new ResourceNotFoundException("Category", "id", cId));
		category.setTitle(categoryDTO.getTitle());
		category.setDescription(category.getDescription());
		Category response = categoryRepo.save(category);
		return modelMapper.map(response, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategoryById(Integer cId) {
		Category category = categoryRepo.findById(cId).orElseThrow(()->new ResourceNotFoundException("Category", "id", cId));
		return modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDTO> categoryDTOs = categories.stream().map((cate)-> modelMapper.map(cate, CategoryDTO.class)).collect(Collectors.toList());
		return categoryDTOs;
	}

	@Override
	public void deleteCategory(Integer cId) {
		Category category = categoryRepo.findById(cId).orElseThrow(()->new ResourceNotFoundException("Category", "id", cId));
		categoryRepo.delete(category);
	}

}
