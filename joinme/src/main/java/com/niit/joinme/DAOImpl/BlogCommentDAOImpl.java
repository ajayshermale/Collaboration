package com.niit.joinme.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.joinme.DAO.BlogCommentDAO;
import com.niit.joinme.model.BlogComment;

@Repository
public class BlogCommentDAOImpl implements BlogCommentDAO{


	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addBlog(BlogComment blogComment) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(blogComment);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<BlogComment> listCommentsByBlogId(int blog_id) {
		// TODO Auto-generated method stub
		String hql = "from BlogComment where blog_Id="+blog_id;
		List<BlogComment> comments = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return comments;
	}

	public void deleteBlogComment(int commentId) {
		// TODO Auto-generated method stub
		BlogComment commentDelete = new BlogComment();
		commentDelete.setCommentId(commentId);
		this.sessionFactory.getCurrentSession().delete(commentDelete);
	}

}
