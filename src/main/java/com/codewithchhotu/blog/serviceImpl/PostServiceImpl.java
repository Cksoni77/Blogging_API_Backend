package com.codewithchhotu.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithchhotu.blog.entities.Category;
import com.codewithchhotu.blog.entities.Post;
import com.codewithchhotu.blog.entities.User;
import com.codewithchhotu.blog.exception.ResourceNotFoundException;
import com.codewithchhotu.blog.payload.PostDto;
import com.codewithchhotu.blog.payload.PostResponse;
import com.codewithchhotu.blog.repository.CategoryRepo;
import com.codewithchhotu.blog.repository.PostRepo;
import com.codewithchhotu.blog.repository.UserRepo;
import com.codewithchhotu.blog.service.PostService;


@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(
				()-> new ResourceNotFoundException("Category","ID",categoryId));
		
		User user = this.userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User","ID",userId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post saved = this.postRepo.save(post);
		return this.modelMapper.map(saved,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Post","ID",postId));
		
		post.setPostTittle(postDto.getPostTittle());
		post.setPostContent(postDto.getPostContent());
		post.setImageName(postDto.getImageName());
		
		Post saved = this.postRepo.save(post);
		return this.modelMapper.map(saved, PostDto.class);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Post","ID",postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}


	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(
				()-> new ResourceNotFoundException("Category","ID",categoryId));
		
		List<Post> allCategory = this.postRepo.findByCategory(category);
		List<PostDto> allPosts = allCategory.stream()
				.map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPosts;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User","ID",userId));
		List<Post> allUsers = this.postRepo.findByUser(user);
		List<PostDto> allPosts = allUsers.stream()
				.map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPosts;
	}


	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Post","ID",postId));
		
		this.postRepo.delete(post);		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		
		
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);		
		 Page<Post> pagePost = this.postRepo.findAll(p);
		 List<Post> content = pagePost.getContent();
		List<PostDto> postDtos = content.stream()
				.map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastpage(pagePost.isLast());
		
		
		return postResponse;
		
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> postTittleContaining = this.postRepo.findByPostTittleContaining(keyword); 
		List<PostDto> postDtos = postTittleContaining.stream()
				.map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}


}
