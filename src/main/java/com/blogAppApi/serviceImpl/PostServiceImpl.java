package com.blogAppApi.serviceImpl;




import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogAppApi.entites.Categary;
import com.blogAppApi.entites.Post;
import com.blogAppApi.entites.User;
import com.blogAppApi.exception.ResorceNotFoundException;
import com.blogAppApi.helper.PostResponce;
import com.blogAppApi.paylods.PostDto;
import com.blogAppApi.repositary.CategaryRepo;
import com.blogAppApi.repositary.PostRepo;
import com.blogAppApi.repositary.UserRepositary;
import com.blogAppApi.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postrepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepositary userRepo;

	@Autowired
	private CategaryRepo categaryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer id, Integer categaryId) {
	
	   log.info("");	
	   User newuser = this.userRepo.findById(id).orElseThrow(()-> new ResorceNotFoundException("User", "userId", id));
	   Categary newcategary = this.categaryRepo.findById(categaryId).orElseThrow(()-> new ResorceNotFoundException("Categary", "categryId", categaryId));
	   Post post = this.modelMapper.map(postDto, Post.class);
	   post.setImageName("default.png");
	   post.setAddedDate(new Date());
	   post.setUser(newuser);
	   post.setCategary(newcategary);	
	   Post newpost = this.postrepo.save(post);
	   log.info("");
	   return this.modelMapper.map(newpost, PostDto.class);
	}

	@Override
	public PostDto updatePostDto(PostDto postDto, Integer postId) {
		log.info("");
		Post post = this.postrepo.findById(postId).orElseThrow(()->new ResorceNotFoundException("Post", "post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatePost = this.postrepo.save(post);
		log.info("");
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		log.info("");
		Post post = this.postrepo.findById(postId).orElseThrow(()->new ResorceNotFoundException("Post", "post id", postId));
		log.info("");
		this.postrepo.delete(post);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		log.info("");
		Post post = this.postrepo.findById(postId).orElseThrow(()->new ResorceNotFoundException("Post", "post id", postId));
		log.info("");
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponce getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		log.info("");
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
			}else {
				sort = Sort.by(sortBy).descending();
			}
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> Post = this.postrepo.findAll(p);
		List<Post> allposts = this.postrepo.findAll();
		List<PostDto> postDto = allposts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponce postResponce = new PostResponce();
		postResponce.setContent(postDto);
		postResponce.setPageNumber(Post.getNumber());
		postResponce.setPageSize(Post.getSize());
		postResponce.setTotalElements(Post.getTotalElements());
		postResponce.setTotalPages(Post.getTotalPages());
		postResponce.setLastPage(Post.isLast());
		log.info("");
		return postResponce;
	}

	@Override
	public List<PostDto> getPostByCategary(Integer categaryId) {
 
		log.info("");
		Categary cat = this.categaryRepo.findById(categaryId)
				.orElseThrow(()->new ResorceNotFoundException("Categary", "categary id", categaryId));
		List<Post> posts = this.postrepo.findByCategary(cat);
		
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		log.info("");
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		log.info("");
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResorceNotFoundException("User", "userId", userId));
		List<Post> posts = this.postrepo.findByUser(user);
		
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
		log.info("");
		return postDtos;
	}

	@Override
	public List<PostDto> serchPost(String Keyword) {
		log.info("");
		List<Post> posts = this.postrepo.searchByTitle(Keyword);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		log.info("");
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPost() {
		log.info(" Initiated Request  for getting All post ");
		List<Post> list = this.postrepo.findAll();
		List<PostDto> list2 =list.stream().map((post)->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		log.info(" completed Request  for getting All post ");
		return list2;
	}
}
