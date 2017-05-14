package com.niit.joinme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.niit.joinme.DAOImpl.ForumDAOImpl;
import com.niit.joinme.model.Forum;

@Service
@Transactional
public class ForumService {

	@Autowired
	ForumDAOImpl forumDAOImpl;
	
	public List<Forum> getAllForums()
	{
		return forumDAOImpl.getAllForums();
		
	}
	public Forum getById(int forum_id)
	{
		return forumDAOImpl.getById(forum_id);
		
	}
	public boolean saveForum(Forum forum)
	{
		return forumDAOImpl.saveForum(forum);
		
	}
	public void deleteForum(int forum_id)
	{
		forumDAOImpl.deleteForum(forum_id);
	}
	public Forum getView(int forum_id)
	{
		return forumDAOImpl.getView(forum_id);
	}
	public void updateForum(Forum forum)
	{
		forumDAOImpl.updateForum(forum);
	}
	
	
}
