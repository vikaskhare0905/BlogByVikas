package com.blogAppApi.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogAppApi.entites.User;
@Repository
public interface UserRepositary extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String username);

}
