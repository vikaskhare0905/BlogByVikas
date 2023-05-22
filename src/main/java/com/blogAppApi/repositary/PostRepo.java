package com.blogAppApi.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogAppApi.entites.Categary;
import com.blogAppApi.entites.Post;
import com.blogAppApi.entites.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user );
	
	List<Post> findByCategary(Categary categary);

	List<Post> searchByTitle(String keyword);

	List<Post> findByTitleContaining(Object keywords);
	
//	@Query("select p from Post p where p.title like : key")
//	List<Post> searchByTitle(@Param("key")String title);

}
