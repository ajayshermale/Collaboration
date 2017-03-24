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
import com.niit.joinme.model.Forum;
import com.niit.joinme.service.ForumService;

@RestController
public class ForumController {

	@Autowired
	private ForumService forumService;
	
	@RequestMapping(value="/forum/{forum_id}", method = RequestMethod.GET )
	public ResponseEntity<Forum> getById(@PathVariable("forum_id") Integer forum_id) {
		Forum forum = forumService.getById(forum_id);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/forum", method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> getAllForums() {
		List<Forum> ForumList = forumService.getAllForums();
		return new ResponseEntity<List<Forum>>(ForumList, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/forum", method = RequestMethod.POST)
	public ResponseEntity<Void> saveForum(@RequestBody Forum forum, UriComponentsBuilder builder)
	{

		
		 Date date=new Date();
		 forum.setCreatedDate(date);
        boolean flag = forumService.saveForum(forum);
               if (flag == true) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/forum{forum_id}").buildAndExpand(forum.getForum_id()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/forum/{forum_id}", method = RequestMethod.PUT )
	public ResponseEntity<Forum> updateForum(@RequestBody Forum forum) {
		forumService.updateForum(forum);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	@RequestMapping(value="/forum/{forum_id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteForum(@PathVariable("forum_id") Integer forum_id) {
		forumService.deleteForum(forum_id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}
