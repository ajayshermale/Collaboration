package com.niit.joinme.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.joinme.DAOImpl.BlogLikeDAOImpl;
import com.niit.joinme.model.BlogLike;

@Service
@Transactional
public class BlogLikeService {
	@Autowired
	private BlogLikeDAOImpl blogLikeDAO;
	@Transactional
	public boolean addBlogLike(BlogLike blogLike) {
		
			blogLikeDAO.addBlogLike(blogLike);
			return true;
		}
	
	public List<BlogLike> listAllLikesByBlogId(int blog_id) {
		return blogLikeDAO.listAllLikesByBlogId(blog_id);
	}
	
	public void deleteBlogLike(int blogLikeId) {
		System.out.println("blog like id..."+blogLikeId);
		blogLikeDAO.deleteBlogLike(blogLikeId);
	}

}
