package com.blogAppApi.service;

import org.springframework.stereotype.Service;

import com.blogAppApi.paylods.CommentDto;

@Service
public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);
}
