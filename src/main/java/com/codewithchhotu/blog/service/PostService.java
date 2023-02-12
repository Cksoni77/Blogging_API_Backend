package com.codewithchhotu.blog.service;

import java.util.List;

import com.codewithchhotu.blog.payload.PostDto;
import com.codewithchhotu.blog.payload.PostResponse;
//import com.codewithchhotu.blog.payload.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer postId);
	PostDto getPostById(Integer postId);
	
	//List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	//PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer userId);
	List<PostDto> searchPost(String keyword);
	void deletePost(Integer postId);
	
	
	

}
