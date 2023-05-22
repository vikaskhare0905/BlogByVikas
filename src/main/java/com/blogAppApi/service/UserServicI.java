package com.blogAppApi.service;

import java.util.List;



import com.blogAppApi.paylods.UserDto;

public interface UserServicI {
	
	UserDto registerNewUser(UserDto user);
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUser();
	
	String deletUser(Integer userId);
	
	

}
