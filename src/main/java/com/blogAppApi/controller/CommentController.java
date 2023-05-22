package com.blogAppApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogAppApi.helper.ApiResponce;
import com.blogAppApi.paylods.CommentDto;
import com.blogAppApi.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/")
@Slf4j
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	/**
	 * @author Vikas Khare
	 * @apiNote this api to create comment
	 * @param commentDto
	 * @param postId
	 * @return
	 */
	
	@PostMapping("/comments/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer commentId){
		log.info(" Starting Request  for create comment with the postid :{} ",commentId);
		CommentDto createComment = this.commentService.createComment(commentDto, commentId);
		log.info(" Ending Request  for create comment with the postid :{} ",commentId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
		
	}
	
	
	/**
	 * @author Vikas Khare
	 * @apiNote this api to delete post by commentId
	 * @param commentId
	 * @return
	 */
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponce> deleteComment(@PathVariable Integer commentId)
	{
		log.info(" Starting Request  for delete comment with the comment id  :{} ",commentId);
		this.commentService.deleteComment(commentId);
		log.info(" Starting Request  for delete comment with the comment id :{} ",commentId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Comment deleted succesfully...", true), HttpStatus.OK);
	}
}
