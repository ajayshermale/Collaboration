package com.niit.joinme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.niit.joinme.model.Friend;
import com.niit.joinme.service.FriendService;
import com.niit.joinme.service.UserService;

@RestController
public class FriendController {

	@Autowired
	private FriendService friendService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/sendFriendRequest/{friendId}", method=RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Friend friend, @PathVariable("friendId") Integer friendId, UriComponentsBuilder builder){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String userName = authentication.getName();
	      int user_id = userService.loadUserByUsername(userName).getUserId();
	      friend.setUser_id(user_id);
	      friend.setFriendId(friendId);
	      friend.setStatus('N');
	      friend.setIsOnline('N');
	      boolean flag = friendService.save(friend);
          if (flag == false) {
   	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
          }
          HttpHeaders headers = new HttpHeaders();
          headers.setLocation(builder.path("/sendFriendRequest/{friendId}").buildAndExpand(friend.getFriendId()).toUri());
          return new ResponseEntity<Void>(headers, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value="/getAllFriendRequest", method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getAllFriendRequests(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String userName = authentication.getName();
	      int user_id = userService.loadUserByUsername(userName).getUserId();
	      List<Friend> list = friendService.getAllFriendRequests(user_id);
		return new ResponseEntity<List<Friend>>(list,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/acceptFriendRequest/{userId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("userId")Integer userId){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String userName = authentication.getName();
	   //   int friendId = userService.getByUsername(username).getUserId();
	      int friendId = userService.loadUserByUsername(userName).getUserId();
	      friendService.update(friendId, userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/rejectFriendRequest/{userId}", method=RequestMethod.PUT)
	public ResponseEntity<Void> reject(@PathVariable("userId")Integer userId){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String userName = authentication.getName();
	      int friendId = userService.loadUserByUsername(userName).getUserId();
	      friendService.reject(friendId, userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/friends", method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getAllFriends(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String userName = authentication.getName();
	      int user_id = userService.loadUserByUsername(userName).getUserId();
	      List<Friend> list = friendService.getAllFriends(user_id);
		return new ResponseEntity<List<Friend>>(list,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/friend", method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> listFriends(){
	      List<Friend> list = friendService.listFriends();
		return new ResponseEntity<List<Friend>>(list,HttpStatus.OK);		
	}

}
