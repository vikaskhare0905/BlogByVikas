package com.blogAppApi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogAppApi.helper.ApiResponce;
import com.blogAppApi.paylods.CategaryDto;
import com.blogAppApi.service.CategaryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/categaries")
public class CategaryController {
	@Autowired
	private CategaryService categaryService;
	
	/**
	 * @author Vikas Khare
	 * @apiNote this api to create categary 
	 * @param categaryDto
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<CategaryDto> createCategary(@Valid @RequestBody CategaryDto categaryDto){
		log.info(" Initiated Request for creating category");
		CategaryDto createCategary = this.categaryService.createCategaryDto(categaryDto);  
		log.info(" completed Request for creating category");
		return new ResponseEntity<CategaryDto>(createCategary,HttpStatus.CREATED);
		
	}

	
	/**
	 * @author Vikas Khare
	 * @apiNote to create categary api
	 * @param categaryDto
	 * @return
	 */
	@PutMapping("/categaries/{categaryId}")
	public ResponseEntity<CategaryDto> updateCategary(@Valid @RequestBody CategaryDto categaryDto, @PathVariable Integer categaryId){
		log.info(" Initiated Request for updating category with categoryId :{}", categaryId); 
		CategaryDto updateCategaryDto = this.categaryService.updateCategaryDto(categaryDto, categaryId);
		log.info(" completed Request for updating category with categoryId :{}", categaryId);
		return new ResponseEntity<CategaryDto>(updateCategaryDto,HttpStatus.CREATED);
		

}
	
	
	/**
	 * @author Vikas Khare
	 * @apiNote this api to get categary using categaryId
	 * @param categaryDto
	 * @return
	 */
	@GetMapping("/{categaryId}")
	public ResponseEntity<CategaryDto>getCategary( @PathVariable Integer categaryId){
		log.info(" Initiated Request for getting category with categoryId :{}", categaryId);
		CategaryDto singaleCategaryDto = categaryService.getSingaleCategaryDto(categaryId);
		log.info(" completed Request for getting category with categoryId :{}", categaryId);
		return new ResponseEntity<CategaryDto>(singaleCategaryDto,HttpStatus.OK);
		
}
	
	
	/**
	 * @author Vikas Khare
	 * @apiNote this api to get all  categaries
	 * @param categaryDto
	 * @return
	 */
	@GetMapping("/categaries")
	public ResponseEntity<List<CategaryDto>>getAllCategary(){
		log.info(" Initiated Request for getting All categories");
		List<CategaryDto> allCategaryDto = this.categaryService.getAllCategaryDto();
		log.info(" completed Request for getting All categories");
		return new ResponseEntity<List<CategaryDto>>(allCategaryDto,HttpStatus.OK);
	}
	
	/**
	 * @author Vikas Khare
	 * @apiNote this api to delete categary using categary Id
	 * @param categaryDto
	 * @return
	 */
	@DeleteMapping("/{categaryId}")
	public ResponseEntity<ApiResponce> deleteCategary( @PathVariable Integer categaryId){
		log.info(" Initiated Request for deleting category with categoryId :{}", categaryId);
		categaryService.deleteCategaryDto(categaryId);
		log.info(" completing Request for deleting category with categoryId :{}", categaryId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("delete succesfully",false),HttpStatus.OK);
		
}
}