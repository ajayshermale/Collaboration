package com.niit.joinme.DAO;

import java.util.List;

import com.niit.joinme.model.DSUser;

public interface UserDAO {

	public List<DSUser> getAllUsers();
	
	public DSUser getById(int user_id);
	
	public boolean saveUser(DSUser user);
	
	public void updateUser(DSUser user);
	
	public void deleteUser(int user_id);
}
