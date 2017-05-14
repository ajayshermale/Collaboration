package com.niit.joinme.DAO;

import java.util.List;

import com.niit.joinme.model.Friend;

public interface FriendDAO {

	public boolean save(Friend friend);
	public List<Friend> getAllFriendRequests(int user_id);
	public List<Friend> getAllFriends(int user_id);
	public List<Friend> listFriends();
	public void update(int friendId, int user_id);
	public void reject(int friendId, int user_id);
}
