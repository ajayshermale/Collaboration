package com.niit.joinme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.joinme.DAOImpl.BlogDAOImpl;
import com.niit.joinme.model.Blog;
@Service
@Transactional
public class BlogService {

	@Autowired
	BlogDAOImpl blogDAOImpl;
	
	public List<Blog> getAllBlogs()
	{
		return blogDAOImpl.getAllBlogs();
		
	}
	public Blog getById(int blog_id)
	{
		return blogDAOImpl.getById(blog_id);
		
	}
	public boolean saveBlog(Blog blog)
	{
		return blogDAOImpl.saveBlog(blog);
		
	}
	public void deleteBlog(int blog_id)
	{
		 blogDAOImpl.deleteBlog(blog_id);
	}
	public Blog getView(int  blog_id)
	{
		return blogDAOImpl.getView(blog_id);
	}
	public void updateBlog(Blog blog)
	{
		blogDAOImpl.updateBlog(blog);
	}
	
}

