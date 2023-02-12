package com.codewithchhotu.blog.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithchhotu.blog.entities.Category;
import com.codewithchhotu.blog.entities.Post;
import com.codewithchhotu.blog.entities.User;
//import com.codewithchhotu.blog.serviceImpl.Page;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	//List<Post> findByTittleContaining(String Tittle);
	List<Post> findByPostTittleContaining(String postTittle);
	
	

}
