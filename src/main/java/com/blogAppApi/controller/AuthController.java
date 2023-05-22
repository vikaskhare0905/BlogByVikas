package com.blogAppApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogAppApi.exception.ApiException;
import com.blogAppApi.helper.ApiResponce;
import com.blogAppApi.helper.JwtAuthRequest;
import com.blogAppApi.helper.JwtAuthResponce;
import com.blogAppApi.paylods.UserDto;
import com.blogAppApi.security.JwtTokenHelper;
import com.blogAppApi.service.UserServicI;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServicI userServiceI;

	/**
	 * @author Vikas Khare
	 * @apiNote to login 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponce> createToken(@RequestBody JwtAuthRequest request) throws Exception {

		this.authenticate(request.getUsername(), request.getPassword());

		UserDetails userDetail = this.userDetailsService.loadUserByUsername(request.getUsername());

		String token = this.jwtTokenHelper.generateToken(userDetail);

		JwtAuthResponce responce = new JwtAuthResponce();

		responce.setToken(token);

		return new ResponseEntity<JwtAuthResponce>(responce, HttpStatus.OK);

	}

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("invalid Details..!");
			throw new ApiException("invalid username or password..!");
		}
	}

	/**
	 * @author Vikas Khare
	 * @apiNote to register
	 * @param userDto
	 * @return
	 */
	
// register new user API
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {

		UserDto registerUser = this.userServiceI.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registerUser, HttpStatus.CREATED);

	}

}
