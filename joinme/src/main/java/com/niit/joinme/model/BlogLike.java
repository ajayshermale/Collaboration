package com.niit.joinme.model;

import javax.persistence.CascadeType;
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
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="user_id",nullable=false, updatable=false, insertable=false)
	private DSUser user;
	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="blog_id",nullable=false,insertable=false,updatable=false)
    private Blog blog;

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

	public DSUser getUser() {
		return user;
	}

	public void setUser(DSUser user) {
		this.user = user;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	
}
