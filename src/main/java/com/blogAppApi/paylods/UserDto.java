package com.blogAppApi.paylods;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.blogAppApi.entites.Role;

import lombok.Data;


@Data
public class UserDto {
	
	private Integer id;
	
	@NotEmpty
	@Size(min=4,message="Name must be above 4 charector..!!")
	private String name;
	
	@Email(message="Invalid email...!!")
	private String email;
	
	@NotEmpty
	@Size(min=4,max=10,message="Password is not valid please provide proper password..!!")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<Role> roles = new HashSet<>();
	
	
	
	

}
