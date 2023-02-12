package com.codewithchhotu.blog.service;

import com.codewithchhotu.blog.payload.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId);
	void delete(Integer commentId);

}
