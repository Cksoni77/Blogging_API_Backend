package com.codewithchhotu.blog.service;

import java.util.List;

import com.codewithchhotu.blog.payload.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryDtoId);
	CategoryDto getCategoryById(Integer categoryDtoId);
	List<CategoryDto> getAllCategory();
	void deleteCategory(Integer categoryDtoId);

}
