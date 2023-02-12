package com.codewithchhotu.blog.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithchhotu.blog.entities.Comment;
import com.codewithchhotu.blog.entities.Post;
import com.codewithchhotu.blog.exception.ResourceNotFoundException;
import com.codewithchhotu.blog.payload.CommentDto;
import com.codewithchhotu.blog.repository.CommentRepo;
//import com.codewithchhotu.blog.payload.PostDto;
import com.codewithchhotu.blog.repository.PostRepo;
import com.codewithchhotu.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CommentRepo commentRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Post","ID",postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment saved = this.commentRepo.save(comment);
		//comment.setContent(commentDto.getContent());
		
		return this.modelMapper.map(saved, CommentDto.class);
	}

	@Override
	public void delete(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(
				()-> new ResourceNotFoundException("Comment","ID",commentId));
		this.commentRepo.delete(comment);
	}

}
