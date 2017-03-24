package com.niit.joinme.DAO;

import java.util.List;

import com.niit.joinme.model.Forum;

public interface ForumDAO {
	
	
public List<Forum> getAllForums();
	
	public Forum getById(int forum_id);
	
	public boolean saveForum(Forum forum);
	
	public void updateForum(Forum forum);
	
	public void deleteForum(int forum_id);
	
	public Forum getView(int forum_id);
}

