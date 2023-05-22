package com.blogAppApi.service;

import java.util.List;

import com.blogAppApi.entites.Post;
import com.blogAppApi.helper.PostResponce;
import com.blogAppApi.paylods.PostDto;

public interface PostService {

	// create post
	PostDto createPost(PostDto postDto, Integer id,Integer categaryId);
	
    // update post by id
	PostDto updatePostDto(PostDto postDto, Integer postId);
	
	// delete post by id 
     void deletePost(Integer postId);
     
     // get post by id 
     PostDto getPostById(Integer id);
     
     // get all posts
     PostResponce getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
     
     // get all post by category 
     List<PostDto> getPostByCategary(Integer categaryId);
     
     // get all posts by user
     List <PostDto> getAllPostByUser(Integer id);
     
     //search post
     List<PostDto> serchPost(String Keywotrd);

	List<PostDto> getAllPost();
}
