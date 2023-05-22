package com.blogAppApi.helper;

import java.util.List;

import com.blogAppApi.paylods.PostDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponce {

	private List<PostDto> content;
	
	private long pageNumber;
	
	private long pageSize;
	
	private long totalElements;
	
	private long totalPages;
	
	private boolean lastPage;
}
