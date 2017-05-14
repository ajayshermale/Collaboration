package com.niit.joinme.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.joinme.DAO.ForumDAO;
import com.niit.joinme.model.Forum;
@Repository
public class ForumDAOImpl implements ForumDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Forum> getAllForums() {
		List<Forum> ForumList = this.sessionFactory.getCurrentSession().createQuery("from Forum").getResultList();
		
		return ForumList;
	}

	public Forum getById(int forum_id) {
		String sql = "from Forum where forum_id=" +forum_id;
		//we need to convert this sql to db specific query... sql is variable name can be any thing
		List<Forum> ForumList = sessionFactory.getCurrentSession().createQuery(sql).getResultList();
		if(ForumList!=null && !ForumList.isEmpty())
		{
			return ForumList.get(0);
		}
		else return null;
	}

	public boolean saveForum(Forum forum) {
		sessionFactory.getCurrentSession().save(forum);
		return false;
	}

	public void updateForum(Forum forum) {
		sessionFactory.getCurrentSession().update(forum);
		
	}

	public void deleteForum(int forum_id) {
		Forum forumToDelete = new Forum();	
		forumToDelete.setForum_id(forum_id);
		   sessionFactory.getCurrentSession().delete(forumToDelete);
		
	}

	public Forum getView(int forum_id) {
		String sql = "from Forum where forum_id=" +forum_id;
		//we need to convert this sql to db specific query... sql is variable name can be any thing
		List<Forum> ForumList = sessionFactory.getCurrentSession().createQuery(sql).getResultList();
		if(ForumList!=null && !ForumList.isEmpty())
		{
			return ForumList.get(0);
		}
		else return null;
	}
	
	
	
}
