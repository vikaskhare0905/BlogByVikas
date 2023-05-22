package com.blogAppApi.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogAppApi.entites.User;
import com.blogAppApi.exception.ResorceNotFoundException;
import com.blogAppApi.repositary.UserRepositary;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepositary userRepositary;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepositary.findByEmail(username)
				.orElseThrow(() -> new ResorceNotFoundException("User", "email :" + username, 0));
		return user;
	}

}
