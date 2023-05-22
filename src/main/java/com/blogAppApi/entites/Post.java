package com.blogAppApi.entites;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="postTitle", nullable=false)
	private String title;

	private String imageName;
	
	private  String content;
	
	private Date addedDate;
	

	@ManyToOne
	@JoinColumn(name="categaryId")
	private Categary categary;

	@ManyToOne
	private User user;

//	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//	private Set<Comment> commments = new HashSet<>();

}
