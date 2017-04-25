package com.niit.joinme.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.joinme.DAOImpl.BlogCommentDAOImpl;
import com.niit.joinme.model.BlogComment;


@Service
@Transactional
public class BlogCommentService {
	@Autowired
	private BlogCommentDAOImpl blogCommentDAOImpl;
	
	public boolean  saveBlogComment(BlogComment blogComment){
		blogCommentDAOImpl. saveBlogComment(blogComment);
		return true;
		
	}
	
	public List<BlogComment> listCommentsByBlogId(int blog_id) {
		return blogCommentDAOImpl.listCommentsByBlogId(blog_id);
		
	}
	public void deleteBlogComment(int commentId) {
		blogCommentDAOImpl.deleteBlogComment(commentId);
	}

}


