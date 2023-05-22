package com.blogAppApi.paylods;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategaryDto {
	
	private Integer id;
	
	@NotBlank
	@Size(min=4,message="Minimum category tital is of  4 charector......!")
	private String categaryTitle;
	
	@NotBlank
	@Size(min =5,message="Minimunm category Discripsion is of 5 charector....!")
	private String categaryDescripsion;

}
