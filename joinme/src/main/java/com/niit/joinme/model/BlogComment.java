package com.niit.joinme.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class BlogComment {


		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int commentId; 
		private int user_id;
		private int blog_id;
		private String blogComment;
		private Date commentDate;
		
		@ManyToOne(cascade=CascadeType.REMOVE)
		@JoinColumn(name="user_id",nullable=false,insertable=false,updatable=false)
		private DSUser user;
		
		@ManyToOne(cascade=CascadeType.REMOVE)
		@JoinColumn(name="blog_id",nullable=false,insertable=false,updatable=false)
		private Blog blog;

		public int getCommentId() {
			return commentId;
		}

		public void setCommentId(int commentId) {
			this.commentId = commentId;
		}

		public int getUserId() {
			return user_id;
		}

		public void setUserId(int user_id) {
			this.user_id = user_id;
		}

		public int getBlogId() {
			return blog_id;
		}

		public void setBlogId(int blog_id) {
			this.blog_id = blog_id;
		}

		public String getBlogComment() {
			return blogComment;
		}

		public void setBlogComment(String blogComment) {
			this.blogComment = blogComment;
		}

//		public DSUser getUser() {
//			return user;
//		}
//
//		public void setUser(DSUser user) {
//			this.user = user;
//		}

		public Blog getBlog() {
			return blog;
		}

		public void setBlog(Blog blog) {
			this.blog = blog;
		}

		public Date getCommentDate() {
			return commentDate;
		}

		public void setCommentDate(Date commentDate) {
			this.commentDate = commentDate;
		}
		
		
		
	}

	
	
	
	

