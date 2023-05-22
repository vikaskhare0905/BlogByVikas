package com.blogAppApi.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogAppApi.entites.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
