package com.blogAppApi.service;

import java.util.List;

import com.blogAppApi.paylods.CategaryDto;

public interface CategaryService {
	
	public CategaryDto createCategaryDto(CategaryDto categaryDto);
	
	public CategaryDto updateCategaryDto(CategaryDto categaryDto, Integer categaryId );

	
	public CategaryDto getSingaleCategaryDto(Integer categaryId);
	
	public List<CategaryDto> getAllCategaryDto();

	
	public void deleteCategaryDto(Integer categaryId);


}
