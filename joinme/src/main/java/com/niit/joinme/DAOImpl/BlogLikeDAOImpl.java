package com.niit.joinme.DAOImpl;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.niit.joinme.model.BlogLike;

@Repository
public class BlogLikeDAOImpl {
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addBlogLike(BlogLike blogLike) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(blogLike);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<BlogLike> listAllLikesByBlogId(int blog_id) {
		// TODO Auto-generated method stub
		String hql = "from BlogLike where blog_id="+blog_id;
		List<BlogLike> likes = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return likes;
	}
	
	public void deleteBlogLike(int blogLikeId) {
		// TODO Auto-generated method stub
		BlogLike likeDelete = new BlogLike();
		likeDelete.setBlogLikeId(blogLikeId);
		this.sessionFactory.getCurrentSession().delete(likeDelete);
	}




}
