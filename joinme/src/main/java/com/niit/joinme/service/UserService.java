package com.niit.joinme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.joinme.DAOImpl.UserDAOImpl;
import com.niit.joinme.model.Blog;
import com.niit.joinme.model.DSUser;

@Service
@Transactional
public class UserService
{
	@Autowired
	UserDAOImpl userDAOImpl;
	
	public List<DSUser> getAllUsers()
	{
		return userDAOImpl.getAllUsers();
		
	}
	public DSUser getById(int user_id)
	{
		return userDAOImpl.getById(user_id);
		
	}
	public boolean saveUser(DSUser user)
	{
		return userDAOImpl.saveUser(user);
		
	}
	public void deleteUser(int user_id)
	{
		 userDAOImpl.deleteUser(user_id);
	}

	public void updateUser(DSUser user)
	{
		userDAOImpl.updateUser(user);
	}
	
}
