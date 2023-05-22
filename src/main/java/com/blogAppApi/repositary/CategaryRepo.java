package com.blogAppApi.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogAppApi.entites.Categary;


public interface CategaryRepo extends JpaRepository<Categary, Integer> {

}
