package com.codewithchhotu.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithchhotu.blog.payload.ApiResponse;
import com.codewithchhotu.blog.payload.CommentDto;
import com.codewithchhotu.blog.service.CommentService;

@RestController
@RequestMapping("/apis")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComemnt(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		
		CommentDto createdComment = this.commentService.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(createdComment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.delete(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment sucessfully deleted",true),HttpStatus.OK);
	}

}
