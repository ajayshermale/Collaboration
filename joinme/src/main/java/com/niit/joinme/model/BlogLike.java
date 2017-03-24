package com.niit.joinme.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class BlogLike {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogLikeId;
	private int user_id;
	private int blog_id;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false, updatable=false, insertable=false)
	private DSUser dsUser;
}
