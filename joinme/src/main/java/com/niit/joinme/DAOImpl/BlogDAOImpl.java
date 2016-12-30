package com.niit.joinme.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.joinme.DAO.BlogDAO;
import com.niit.joinme.model.Blog;
@Repository
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Blog> getAllBlogs() {
		
		List<Blog> BlogList = this.sessionFactory.getCurrentSession().createQuery("from Blog").getResultList();
		return BlogList;
	}
	
	public Blog getById(int blog_id) {
		String sql = "from Blog where blog_id=" +blog_id;
		//we need to convert this sql to db specific query... sql is variable name can be any thing
		List<Blog> BlogList = sessionFactory.getCurrentSession().createQuery(sql).getResultList();
		if(BlogList!=null && !BlogList.isEmpty())
		{
			return BlogList.get(0);
		}
		else return null;
	}

	public boolean saveBlog(Blog blog) {
		sessionFactory.getCurrentSession().save(blog);
		return false;
	}

	public void updateBlog(Blog blog) {
		sessionFactory.getCurrentSession().update(blog);
		
	}

	public void deleteBlog(int blog_id) {
		Blog blogToDelete = new Blog();	
		blogToDelete.setBlog_id(blog_id);
		   sessionFactory.getCurrentSession().delete(blogToDelete);
	}

	public Blog getView(int  blog_id) {
		String sql = "from Blog where blog_id=" +blog_id;
		//we need to convert this sql to db specific query... sql is variable name can be any thing
		List<Blog> BlogList = sessionFactory.getCurrentSession().createQuery(sql).getResultList();
		if(BlogList!=null && !BlogList.isEmpty())
		{
			return BlogList.get(0);
		}
		else return null;
	}



}


