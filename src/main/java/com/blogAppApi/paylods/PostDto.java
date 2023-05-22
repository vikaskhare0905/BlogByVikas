package com.blogAppApi.paylods;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//validation lagana he in all dto
public class PostDto {
	
	private Integer id;
	
	private String title;
	
	private  String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private UserDto user;
	
	private CategaryDto categary;
	
	private Set<CommentDto> comments = new HashSet<>();

}
