package com.niit.joinme.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.niit.joinme.model.Blog;
import com.niit.joinme.model.DSUser;
import com.niit.joinme.service.UserService;

@RestController
public class UserController 
{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/{user_id}", method = RequestMethod.GET )
	public ResponseEntity<DSUser> getById(@PathVariable("user_id") Integer user_id) {
		DSUser user = userService.getById(user_id);
		return new ResponseEntity<DSUser>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/user", method = RequestMethod.GET)
	public ResponseEntity<List<DSUser>> getAllUsers() {
		List<DSUser> UserList = userService.getAllUsers();
		return new ResponseEntity<List<DSUser>>(UserList, HttpStatus.OK);
	}
		
	@RequestMapping(value= "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> saveUser(@RequestBody DSUser user, UriComponentsBuilder builder)
	{

		
		 Date date=new Date();
		 user.setCreatedDate(date);
        boolean flag = userService.saveUser(user);
               if (flag == true) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/user{user_id}").buildAndExpand(user.getUserId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/user/{user_id}", method = RequestMethod.PUT )
	public ResponseEntity<DSUser> updateUser(@RequestBody DSUser user) {
		userService.updateUser(user);
		return new ResponseEntity<DSUser>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{user_id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteUser(@PathVariable("user_id") Integer user_id) {
		userService.deleteUser(user_id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	

}
