package com.blogAppApi.helper;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String username;
	
	private String password;
}
