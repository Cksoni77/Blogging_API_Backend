package com.codewithchhotu.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.codewithchhotu.blog.entities.Category;
import com.codewithchhotu.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private int postId;
	private String postTittle;
	private String postContent;
	private String imageName;
	private Date addedDate;
	
	private CategoryDto category;
	private UserDto user;
	
	private Set<CommentDto> comment = new HashSet<>();

}
