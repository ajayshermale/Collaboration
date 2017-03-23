package com.niit.joinme.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.joinme.DAO.UserDAO;
import com.niit.joinme.model.DSUser;
@Repository
public class UserDAOImpl implements UserDAO 
{

	@Autowired
	private SessionFactory sessionFactory;
		
    @SuppressWarnings("unchecked")
	public List<DSUser> getAllUsers() {
		List<DSUser> UserList = this.sessionFactory.getCurrentSession().createQuery("from DSUser").getResultList();
		return UserList;
	}
    
    @SuppressWarnings("unchecked")
	public DSUser getById(int user_id) {
		String sql = "from DSUser where user_id=" +user_id;
		//we need to convert this sql to db specific query... sql is variable name can be any thing
		List<DSUser> UserList = sessionFactory.getCurrentSession().createQuery(sql).getResultList();
		if(UserList!=null && !UserList.isEmpty())
		{
			return UserList.get(0);
		}
		else return null;
	}

	public boolean saveUser(DSUser user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return false;	
		}

	public void updateUser(DSUser user) {
		sessionFactory.getCurrentSession().update(user);		
	}

	public void deleteUser(int user_id) {
		DSUser userToDelete = new DSUser();	
		userToDelete.setUserId(user_id);
		   sessionFactory.getCurrentSession().delete(userToDelete);
		
	}

	 private static List<DSUser> UserList;
	  public DSUser findByName(String userName) {
	        for(DSUser user : UserList){
	            if(user.getUsername().equalsIgnoreCase(userName)){
	                return user;
	            }
	        }
	        return null;
	    }
}
