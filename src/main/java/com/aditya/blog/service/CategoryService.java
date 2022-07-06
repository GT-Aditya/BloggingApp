package com.aditya.blog.service;

import java.util.List;

import com.aditya.blog.payload.CategoryDTO;


public interface CategoryService {
	
	CategoryDTO createCategory(CategoryDTO category);

	CategoryDTO updateCategory(CategoryDTO category, Integer cId);

	CategoryDTO getCategoryById(Integer cId);

	List<CategoryDTO> getAllCategories();

	void deleteCategory(Integer cId);

}
