package com.niit.joinme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.joinme.DAOImpl.FriendDAOImpl;
import com.niit.joinme.model.Friend;

@Service
public class FriendService {

	@Autowired
	private FriendDAOImpl friendDAO;
	
	@Transactional
	public synchronized boolean save(Friend friend) {
		friendDAO.save(friend);
		return true;
		
	}
	
	@Transactional
	public List<Friend> getAllFriendRequests(int user_id) {
		return friendDAO.getAllFriendRequests(user_id);
		
	}
	
	@Transactional
	public void update(int friendId, int user_id) {
		friendDAO.update(friendId, user_id);
	}
	
	@Transactional
	public void reject(int friendId, int user_id) {
		friendDAO.reject(friendId, user_id);
	}
	
	@Transactional
	public List<Friend> getAllFriends(int user_id) {
		return friendDAO.getAllFriends(user_id);
	}
	
	@Transactional
	public List<Friend> listFriends() {
		return friendDAO.listFriends();
	}

}
