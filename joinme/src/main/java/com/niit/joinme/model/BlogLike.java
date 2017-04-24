package com.niit.joinme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class BlogLike {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogLikeId;
	private int user_id;
	private int blog_id;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false, updatable=false, insertable=false)
	private DSUser dsUser;
	
	@ManyToOne
	@JoinColumn(name="blog_id",nullable=false, updatable=false, insertable=false)
	private Blog blog;
	

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public int getBlogLikeId() {
		return blogLikeId;
	}

	public void setBlogLikeId(int blogLikeId) {
		this.blogLikeId = blogLikeId;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}

	public DSUser getDsUser() {
		return dsUser;
	}

	public void setDsUser(DSUser dsUser) {
		this.dsUser = dsUser;
	}
	
}
