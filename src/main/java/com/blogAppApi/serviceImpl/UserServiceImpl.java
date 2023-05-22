package com.blogAppApi.serviceImpl;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogAppApi.entites.Role;
import com.blogAppApi.entites.User;
import com.blogAppApi.exception.ResorceNotFoundException;
import com.blogAppApi.helper.AppConstants;
import com.blogAppApi.paylods.UserDto;
import com.blogAppApi.repositary.RoleRepository;
import com.blogAppApi.repositary.UserRepositary;
import com.blogAppApi.service.UserServicI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserServicI {
	@Autowired
	private UserRepositary userRepositary;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepositary;
	

	@Override
	public UserDto createUser(UserDto userDto) {
		log.info("Starting of user saving");
		User user = this.dtoToUser(userDto);
		User save = this.userRepositary.save(user);
		log.info("Endinging of user saving");
		return this.userToDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		log.info("Starting of searching");
		User user = this.userRepositary.findById(userId)
				.orElseThrow(() -> new ResorceNotFoundException("User", "Integer", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userRepositary.save(user);
		UserDto userdto1 = this.userToDto(updatedUser);
		log.info("End of searching");
		return userdto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		log.info("Starting search operation by userId", userId);
		User newUser = this.userRepositary.findById(userId)
				.orElseThrow(() -> new ResorceNotFoundException("User", "Integer", userId));
		UserDto newUser1 = this.userToDto(newUser);
        log.info("End of Search Operation of UserId", userId);
		return newUser1;
	}

	@Override
	public List<UserDto> getAllUser() {
		log.info("Starting for searching all user");
		List<User> users = this.userRepositary.findAll();
		List<UserDto> listUser = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		log.info("End for searching");
		return listUser;
	}

	@Override
	public String deletUser(Integer userId) {
		log.info("Starting of deletion", userId);
		User deletUser = this.userRepositary.findById(userId).orElseThrow(()-> new ResorceNotFoundException("User", "Integer", userId));
	    this.userRepositary.delete(deletUser);
	    log.info("End of deletion", userId);
		return"user deleted succesfully...!";
	}

	private User dtoToUser(UserDto userDto) {
		User user = this.modelmapper.map(userDto, User.class);
		
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;

	}

	private UserDto userToDto(User user) {
		
		UserDto userDto = this.modelmapper.map(user, UserDto.class);
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;

	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		log.info("Statring of set the passwoord");
		User user = this.modelmapper.map(userDto, User.class);
		
		//encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//roles
		Role role = this.roleRepositary.findById(AppConstants.NORM_USER).get();
		user.getRoles().add(role);
		
		User newUser = this.userRepositary.save(user);
		log.info("End of password setting");
		return this.modelmapper.map(newUser, UserDto.class);
	}
}
