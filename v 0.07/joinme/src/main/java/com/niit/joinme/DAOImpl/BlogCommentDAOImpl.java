package com.niit.joinme.DAOImpl;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.joinme.model.BlogComment;


@Repository
public class BlogCommentDAOImpl {
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean  saveBlogComment(BlogComment blogComment) {
	
		sessionFactory.getCurrentSession().saveOrUpdate(blogComment);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<BlogComment> listCommentsByBlogId(int blog_id) {

		String hql = "from BlogComment where blog_Id="+blog_id;
		List<BlogComment> comments = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		return comments;
	}

	public void deleteBlogComment(int commentId) {
		
		BlogComment commentDelete = new BlogComment();
		commentDelete.setCommentId(commentId);
		this.sessionFactory.getCurrentSession().delete(commentDelete);
	}

}
