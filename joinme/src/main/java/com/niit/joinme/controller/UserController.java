package com.niit.joinme.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;


import com.niit.joinme.model.DSUser;
import com.niit.joinme.service.UserService;

@RestController
public class UserController 
{
	
	@Autowired
	private UserService userService;
	//http://localhost:8080/joinme/user/user_id
	//@get mapping("/user/{user_id}")
	@RequestMapping(value="/user/{user_id}", method = RequestMethod.GET )
	public ResponseEntity<DSUser> getById(@PathVariable("user_id") Integer user_id) {
		DSUser user = userService.getById(user_id);
		return new ResponseEntity<DSUser>(user, HttpStatus.OK);
	}
	
//	@RequestMapping(value="/currentUser", method = RequestMethod.GET )
//	public ResponseEntity<DSUser> loadUserByUsername( String userName) {
//		DSUser user = userService.loadUserByUsername(userName);
//		
//		return new ResponseEntity<DSUser>(user, HttpStatus.OK);
//	}
	
//current user logged in fetch all details	
	@RequestMapping(value= "/user/myProfile", method = RequestMethod.GET)
	public ResponseEntity<DSUser> getById(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		System.out.println("user currently reg"+userName);
	      int user_id =userService.loadUserByUsername(userName).getUserId();
	      System.out.println("id is"+ user_id);
	      DSUser user = userService.getById(user_id);
		return new ResponseEntity<DSUser>(user, HttpStatus.OK);
		
	}
//logout user
	@RequestMapping(value="/perform_logout",method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null)
		{
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		
			
		}
		
		return "redirect:/";			
	}
//user image upload

	@RequestMapping(value= "/user/profileUpload", method = RequestMethod.POST)
	public ResponseEntity<Void> addUserProfilePicture(@RequestParam(value="file")MultipartFile file,UriComponentsBuilder builder,DSUser user){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
	      int user_id = userService.loadUserByUsername(userName).getUserId();
	      System.out.println("id is"+ user_id);
	      File newFile;
     		String path = "C:\\Users\\Rahul\\workspace project2 new one\\joinme_frontend\\src\\main\\webapp\\resource\\userimg\\";
     		path = path+user_id+".jpg";
     		newFile = new File(path);
     		System.out.println(path);
     		
     		if(!file.isEmpty())
     		{
     			try {
     				byte[] bytes = file.getBytes();
     				FileOutputStream fos = new FileOutputStream(newFile);
     				BufferedOutputStream bos = new BufferedOutputStream(fos);
     				bos.write(bytes);
     				bos.close();
     			} catch (IOException e) {
     				// TODO Auto-generated catch block
     				System.out.println("exception occured"+e);
     			}			
     		}
     		else{
     				System.out.println("No file selected");
     		}
     		
     		 HttpHeaders headers = new HttpHeaders();
             headers.setLocation(builder.path("/user/{user_id}").buildAndExpand(user_id).toUri());
             return new ResponseEntity<Void>(headers, HttpStatus.CREATED);	
		
		
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
		 user.setEnabled(true);
		 user.setUserRole(1);
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
