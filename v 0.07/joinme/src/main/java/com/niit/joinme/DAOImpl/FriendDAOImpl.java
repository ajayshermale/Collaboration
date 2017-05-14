package com.niit.joinme.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.joinme.DAO.FriendDAO;
import com.niit.joinme.model.Friend;

@Repository
public class FriendDAOImpl implements FriendDAO{

	
	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(Friend friend) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(friend);
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Friend> getAllFriendRequests(int user_id) {
		// TODO Auto-generated method stub
		String hql = "from Friend where Status = 'N' and friendId="+user_id;
		List<Friend> list = sessionFactory.getCurrentSession().createQuery(hql).getResultList(); 
		return list;
	}

	public void update(int friendId, int user_id) {
		// TODO Auto-generated method stub
		
		String hql="update Friend set Status='A' where user_id=" +user_id+"and friendId="+friendId;
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public void reject(int friendId, int user_id) {
		// TODO Auto-generated method stub
		String hql="update Friend set Status='R' where user_id=" +user_id+"and friendId="+friendId;
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Friend> getAllFriends(int user_id) {
		// TODO Auto-generated method stub
	String hql = "from Friend WHERE (user_id="+user_id+"OR friendId="+user_id+")AND (STATUS='A')";
	List<Friend> list = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
	return list;
	}

	@SuppressWarnings("unchecked")
	public List<Friend> listFriends() {
		// TODO Auto-generated method stub
		List<Friend> list = sessionFactory.getCurrentSession().createQuery("from Friend").getResultList();
		return list;
	}
	

}
