package com.codewithchhotu.blog.controller;

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

import com.codewithchhotu.blog.payload.ApiResponse;
import com.codewithchhotu.blog.payload.CategoryDto;
import com.codewithchhotu.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apis/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryDtoId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryDtoId){
		
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryDtoId);
		return ResponseEntity.ok(updatedCategory);
	}
	
	@GetMapping("/{categoryDtoId}")
	public ResponseEntity<CategoryDto> getCategoryById(@RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryDtoId){
		CategoryDto getCategoryById = this.categoryService.getCategoryById(categoryDtoId);
		
		return ResponseEntity.ok(getCategoryById);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		return ResponseEntity.ok(this.categoryService.getAllCategory());
	}
	
	@DeleteMapping("/{categoryDtoId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryDtoId){
		
		this.categoryService.deleteCategory(categoryDtoId);
		return new ResponseEntity(new ApiResponse("Category deleted sucessfully",true),HttpStatus.OK);
	}
			

}
