package com.blogAppApi.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogAppApi.entites.Categary;
import com.blogAppApi.exception.ResorceNotFoundException;
import com.blogAppApi.paylods.CategaryDto;
import com.blogAppApi.repositary.CategaryRepo;
import com.blogAppApi.service.CategaryService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class CategeryServiceImpl implements CategaryService {

	@Autowired
	private CategaryRepo repo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategaryDto createCategaryDto(CategaryDto categaryDto) {
		log.info("Ending Request  for delete comment with the comment id  :{} ");
		Categary cat = this.modelMapper.map(categaryDto, Categary.class);
		Categary addedcat = this.repo.save(cat);
		log.info("Ending Request  for delete comment with the comment id  :{} ");
		return modelMapper.map(addedcat, CategaryDto.class);

	}

	@Override
	public CategaryDto updateCategaryDto(CategaryDto categaryDto, Integer categaryId) {
		log.info("Ending Request  for delete comment with the comment id  :{} ");
		Categary cat = this.repo.findById(categaryId)
				.orElseThrow(() -> new ResorceNotFoundException("Categary", "categaryId", categaryId));
		cat.setCategaryDescripsion(categaryDto.getCategaryDescripsion());
		cat.setCategaryTitle(categaryDto.getCategaryTitle());
		Categary updatedCategary = this.repo.save(cat);
		log.info("Ending Request  for delete comment with the comment id  :{} ");
		return this.modelMapper.map(updatedCategary, CategaryDto.class);

	}

	@Override
	public CategaryDto getSingaleCategaryDto(Integer categaryId) {
		log.info("Ending Request  for delete comment with the comment id  :{} ");
		Categary cat = this.repo.findById(categaryId)
				.orElseThrow(() -> new ResorceNotFoundException("Categary", "categaryId", categaryId));
		log.info("Ending Request  for delete comment with the comment id  :{} ");
		return this.modelMapper.map(cat, CategaryDto.class);
	}

	@Override
	public List<CategaryDto> getAllCategaryDto() {
		log.info("Ending Request  for delete comment with the comment id  :{} ");
		List<Categary> allCategary = this.repo.findAll();
        List<CategaryDto> list = allCategary.stream().map((cat)-> this.modelMapper.map(cat, CategaryDto.class)).collect(Collectors.toList());
        log.info("Ending Request  for delete comment with the comment id  :{} ");
        return list;
	}

	@Override
	public void deleteCategaryDto(Integer categaryId) {
		log.info("Ending Request  for delete comment with the comment id  :{} ");
		Categary newcategaryId = this.repo.findById(categaryId)
				.orElseThrow(() -> new ResorceNotFoundException("Categary", "categaryId", categaryId));
		log.info("Ending Request  for delete comment with the comment id  :{} ");
		this.repo.delete(newcategaryId);

	}

}
