package com.niit.joinme.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Blog {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int blog_id;

	private String title;
	
	private String content;
	
	private Date createdDate;
	
	private int user_id;
    
	private String userName;



	@Transient
	private MultipartFile blogImage;
	
	
	//@JsonBackReference
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="user_id",nullable=false,insertable=false,updatable=false)


	public MultipartFile getBlogImage() {
		return blogImage;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setBlogImage(MultipartFile blogImage) {
		this.blogImage = blogImage;
	}

	public int getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
