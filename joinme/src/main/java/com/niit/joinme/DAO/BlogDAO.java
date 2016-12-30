package com.niit.joinme.DAO;

import java.util.List;

import com.niit.joinme.model.Blog;

public interface BlogDAO {	

		public List<Blog> getAllBlogs();
		
		public Blog getById(int blog_id);
		
		public boolean saveBlog(Blog blog);
		
		public void updateBlog(Blog blog);
		
		public void deleteBlog(int blog_id);
		
		public Blog getView(int blog_id);
		    
		} 

