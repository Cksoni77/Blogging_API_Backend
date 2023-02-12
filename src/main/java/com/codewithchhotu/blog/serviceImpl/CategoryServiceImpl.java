package com.codewithchhotu.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithchhotu.blog.entities.Category;
import com.codewithchhotu.blog.exception.ResourceNotFoundException;
import com.codewithchhotu.blog.payload.CategoryDto;
import com.codewithchhotu.blog.payload.UserDto;
import com.codewithchhotu.blog.repository.CategoryRepo;
import com.codewithchhotu.blog.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryrepo;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.dtoToCategory(categoryDto);
		Category saved = this.categoryrepo.save(category);
		return this.categoryToDto(saved);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryDtoId) {
		Category category = this.categoryrepo.findById(categoryDtoId).orElseThrow(
				()-> new ResourceNotFoundException("Category","ID",categoryDtoId));
		
		category.setCategoryTittle(categoryDto.getCategoryTittle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category saved = this.categoryrepo.save(category);
		return this.categoryToDto(saved);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryDtoId) {
		Category category = this.categoryrepo.findById(categoryDtoId).orElseThrow(
				()-> new ResourceNotFoundException("Category","ID",categoryDtoId));
		
		return this.categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryrepo.findAll();
		List<CategoryDto> collectCategory = categories.stream()
				.map(category->this.categoryToDto(category)).collect(Collectors.toList());
		
		return collectCategory;
	}

	@Override
	public void deleteCategory(Integer categoryDtoId) {
		Category category = this.categoryrepo.findById(categoryDtoId).orElseThrow(
				()-> new ResourceNotFoundException("Category","ID",categoryDtoId));
		this.categoryrepo.delete(category);
		
	}
	
	public Category dtoToCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}
	
	public CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
