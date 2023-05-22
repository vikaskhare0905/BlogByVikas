package com.blogAppApi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogAppApi.paylods.UserDto;
import com.blogAppApi.service.UserServicI;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
	
	@Autowired
	private UserServicI userServicei;
	
	/**
	 * @author Vikas Khare
	 * @apiNote This Api is used to create User
	 * @param userdto
	 * @return
	 */
	@PostMapping("/user")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		log.info(" Initiated Request for creating user");
		UserDto createUser = this.userServicei.createUser(userDto);
		log.info(" Completed Request  for creating user");
		return new ResponseEntity<UserDto>(createUser,HttpStatus.CREATED);
		
	}
	
	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to get user by userId
	 * @param Id
	 * @return
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> getSingalUser(@PathVariable Integer id){
		log.info(" Initiated Request  for getting user with userId :{}", id);
		UserDto userById = this.userServicei.getUserById(id);
		log.info(" Completed Request  for getting user with userId :{}", id);
		return new ResponseEntity<UserDto>(userById,HttpStatus.OK);
		
	}
	
	/**
	 * @author Vikas Khare
	 * @apiNote this api is to update by user
	 * @param userDto
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid   @RequestBody UserDto userDto, @PathVariable Integer id){
		log.info(" Initiated Request for updating Users");
		UserDto updateUser =this.userServicei.updateUser(userDto, id);
		log.info(" Completed Request for updating Users");
		return new ResponseEntity<UserDto>(updateUser,HttpStatus.OK);
		
	}
	
	/**
	 * @author Vikas Khare
	 * @apiNote This Api is used to get All users
	 * @return
	 */
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUser(){
		log.info(" Initiated Request for getting Users");
		List<UserDto> allUser = this.userServicei.getAllUser();
		log.info(" completed Request  for getting users ");
		return new ResponseEntity<List<UserDto>>(allUser,HttpStatus.OK);

}

	/**
	 * @author Vikas Khare
	 * @apiNote This Api is used to delete user by userId
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> deletUser(@PathVariable Integer id){
		log.info(" Initiated Request  for deleting user with userId :{}", id);
    	String deletUser =this.userServicei.deletUser(id);
	    log.info(" Completed Request  for deleting user with userId :{}", id);
		return new ResponseEntity<String>("User deleted",HttpStatus.OK);
	
}
}
