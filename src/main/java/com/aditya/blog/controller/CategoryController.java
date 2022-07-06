package com.aditya.blog.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.blog.payload.ApiResponse;
import com.aditya.blog.payload.CategoryDTO;
import com.aditya.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//create category
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
		return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
	}
	
	//update category
	@PutMapping("/{cId}")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Integer cId){
		return new ResponseEntity<>(categoryService.updateCategory(categoryDTO, cId), HttpStatus.CREATED);
	}
	
	
	//delete category
	@DeleteMapping("/{cId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer cId){
		categoryService.deleteCategory(cId);
		return new ResponseEntity<>(new ApiResponse("categoryDTO deleted successfully", true), HttpStatus.CREATED);
	}
	
	//get category
	@GetMapping("/{cId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer cId){
		return new ResponseEntity<>(categoryService.getCategoryById(cId), HttpStatus.OK);
	}
	
	//get all category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllCategories(){
		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
	}
}
