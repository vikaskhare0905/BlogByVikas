package com.blogAppApi.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogAppApi.entites.Comment;
import com.blogAppApi.entites.Post;
import com.blogAppApi.exception.ResorceNotFoundException;
import com.blogAppApi.paylods.CommentDto;
import com.blogAppApi.repositary.CommentRepo;
import com.blogAppApi.repositary.PostRepo;
import com.blogAppApi.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		log.info("Starting Request for create comment with the postid:{}", postId);
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResorceNotFoundException("Post", "post id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);		
		Comment savedComment = this.commentRepo.save(comment);
		log.info("Ending Request for create comment with the postid:{}", postId);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		log.info("Starting Request  for delete comment with the comment id  :{} ", commentId);
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResorceNotFoundException("Comment", "comment id", commentId));
		log.info("Ending Request  for delete comment with the comment id  :{} ", commentId);
		this.commentRepo.delete(com);
	}

}
