package com.niit.joinme.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class DSUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int user_id;
	private String userName;
	private String password;
	private String userFirstname;
	private String userLastname;
	private String userRole;
	private String createdBy;
	private Date createdDate;
	@Transient
	private MultipartFile userImage;
	
	public MultipartFile getUserImage() {
		return userImage;
	}
	public void setUserImage(MultipartFile userImage) {
		this.userImage = userImage;
	}
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int userId) {
		this.user_id = userId;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String username) {
		this.userName = username;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserFirstname() {
		return userFirstname;
	}
	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}
	public String getUserLastname() {
		return userLastname;
	}
	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
    

}
