package com.niit.joinme.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Friend {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int friendTableId;
	private int user_id;
	private int friendId;
	private char status;
	private char isOnline;

	
	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="user_id",nullable=false,insertable=false,updatable=false)
	private DSUser user;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="friendId", nullable = false, updatable = false, insertable = false)
	private DSUser userFriend;

	public int getFriendTableId() {
		return friendTableId;
	}

	public void setFriendTableId(int friendTableId) {
		this.friendTableId = friendTableId;
	}


	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}

	public DSUser getUser() {
		return user;
	}

	public void setUser(DSUser user) {
		this.user = user;
	}

	public DSUser getUserFriend() {
		return userFriend;
	}

	public void setUserFriend(DSUser userFriend) {
		this.userFriend = userFriend;
	}

	
	
}
