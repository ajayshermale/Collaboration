package com.niit.joinme.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Transient
	private MultipartFile blogImage;
	

	public MultipartFile getBlogImage() {
		return blogImage;
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
