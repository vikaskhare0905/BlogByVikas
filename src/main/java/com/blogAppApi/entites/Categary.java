package com.blogAppApi.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categaryId;
	
	@Column(name="title",nullable = false)
	private String categaryTitle;
	
	@Column(name="descripsion" )
	private String categaryDescripsion;
	
	@OneToMany(mappedBy = "categary",cascade = CascadeType.ALL)
	private List<Post> posts=new ArrayList<>();
	

}
