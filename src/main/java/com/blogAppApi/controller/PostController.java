package com.blogAppApi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogAppApi.entites.Post;
import com.blogAppApi.helper.ApiResponce;
import com.blogAppApi.helper.AppConstants;
import com.blogAppApi.helper.PostResponce;
import com.blogAppApi.paylods.PostDto;
import com.blogAppApi.service.FileService;
import com.blogAppApi.service.PostService;

import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/api")
@Slf4j
public class PostController {
	
	
	@Autowired
	private PostService postservice;
	
	@Autowired
	private FileService fileservice;
	
	@Value("${project.image}")
	private String path;

	/**
	 * @author Vikas Khare
	 * @apiNote this api to create post
	 * @param postDto
	 * @param userId
	 * @param categaryId
	 * @return
	 */
	@PostMapping("/user/{id}/categary/{categaryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categaryId)
	{
		 log.info(" Initiated Request  for creating post ");
		 PostDto createPost = this.postservice.createPost(postDto, userId, categaryId);
		 log.info(" Completed Request  for creating post");
		 return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	//get by user
	/**
	 * @author Vikas Khare
     * @apiNote This Api is used to get All posts using userId
	 * @param userId
	 * @return
	 */
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		log.info(" Initiated Request  for getting All post with userId :{}", userId);
		List<PostDto> posts = this.postservice.getAllPostByUser(userId);
		log.info(" Completed Request  for getting All post with userId :{}", userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
		
	}
	
	
	//get by category
	/**
	 * @author Vikas Khare
	 * @apiNote This Api is used to get All posts using categoryId
	 * @param categaryId
	 * @return
	 */
	@GetMapping("/posts/categary/{categaryId}")
	public ResponseEntity<List<PostDto>> getPostByCategary(@PathVariable Integer categaryId){
		log.info(" Initiated Request  for getting All post with categoryId :{}", categaryId);
		List<PostDto> posts = this.postservice.getPostByCategary(categaryId);
		log.info(" completed Request  for getting All post with categoryId :{}", categaryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
		
	}
	
	
	//get all posts
	/**
	 * @author Vikas Khare
	 * @apiNote This Api is used to perform Pagination
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @param sortDir
	 * @return
	 */
	@GetMapping("/posts/pagination")
	public ResponseEntity<PostResponce> getAllPost(
			@RequestParam(value="pageNumber", defaultValue=AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue=AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue=AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value="sortDir", defaultValue=AppConstants.SORT_DIR, required = false) String sortDir)
	
	{
		log.info(" Initiated Request  for  pagination ");
		PostResponce postResponce = this.postservice.getAllPost(pageNumber, pageSize,sortBy, sortDir);
		log.info(" completing  Request  for   pagination ");
		return new ResponseEntity<PostResponce >(postResponce , HttpStatus.OK);
	}
	
	//get post details by ID
	/**
	 * @author Vikas Khare
	 * @apiNote This Api is used to get post by postId
	 * @param postId
	 * @return
	 */
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		log.info(" Initiated Request  for getting  post with postId :{}", postId);
	    PostDto postDto = this.postservice.getPostById(postId);
	    log.info(" completed Request  for getting  post with postId :{}", postId);
	    return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	/**
	 * @author Vikas Khare
	 * @apiNote This Api is used to get All posts
	 * @return
	 */
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost() {
		log.info(" Initiated Request  for getting All post ");
		List<PostDto> allPost = this.postservice.getAllPost();
		log.info(" completed Request  for getting All post ");
		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.FOUND);
	}
	
	//delete post
	/**
	 * @author Vikas Khare
	 * @apiNote this api to delete post by postId
	 * @param postId
	 * @return
	 */
	@DeleteMapping("/posts/{postId}")
	public ApiResponce deletePost(@PathVariable Integer postId)
	{
		log.info(" Initiated Request  for deleting  post with postId :{}", postId);
		this.postservice.deletePost(postId);
		log.info(" completing Request  for deleting  post with postId :{}", postId);
		return new ApiResponce("Post Is Successfully deleted....!!", true);
	}
	
	//update post 
	/**
	 * @author Vikas Khare
	 * @apiNote this api to update post
	 * @param postDto
	 * @param postId
	 * @return
	 */
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost (@RequestBody PostDto postDto, @PathVariable Integer postId){
		log.info(" Initiated Request  for updating  post with postId :{}", postId);
		PostDto updatePost = this.postservice.updatePostDto(postDto, postId);
		log.info(" completed Request  for updating  post with postId :{}", postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//search
	/**
	 * @author Vikas Khare
	 * @apiNote This Api is used to search post by using title
	 * @param keyword
	 * @return
	 */
	public ResponseEntity<List<PostDto>> searchPostByTitle(
			@PathVariable("keywords") String keywords)
	{
		log.info(" Initiated Request  for Searching  post with keyword :{}", keywords);
		List<PostDto> result = this.postservice.serchPost(keywords);
		log.info(" Initiated Request  for Searching  post with Keyword :{}", keywords);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
		
	}
	
	
	//post image upload
	/**
	 * @author Vikas Khare
	 * @apiNote this api to upload image
	 * @param image
	 * @param postId
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image")MultipartFile image,
			@PathVariable Integer postId
			) throws IOException{
		log.info(" Initiated Request  for uploading Image   with postId :{}", postId);
		String fileName = this.fileservice.uploadingImage(path, image);
		PostDto postDto = this.postservice.getPostById(postId);
		postDto.setImageName(fileName);
		PostDto updatePost = this.postservice.updatePostDto(postDto, postId);
		log.info(" completed Request  for uploading Image  with postId :{}", postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	//method to serve file
	/**
	 * @author Vikas Khare
	 * @apiNote this api to download image
	 * @param imageName
	 * @param response
	 * @throws IOException
	 */
	@GetMapping(value = "/post/image/{imageName}",  produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response)throws IOException
	{
		log.info(" Initiated Request  for accessing Image ");
		InputStream resource = this.fileservice.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		log.info(" completed Request  for accessing Image ");
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
	
	
}
