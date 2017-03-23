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
import com.niit.joinme.service.BlogService;

@RestController
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value="/blog/{blog_id}", method = RequestMethod.GET )
	public ResponseEntity<Blog> getById(@PathVariable("blog_id") Integer blog_id) {
		Blog blog = blogService.getById(blog_id);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/blog", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getAllBlogs() {
		List<Blog> BlogList = blogService.getAllBlogs();
		return new ResponseEntity<List<Blog>>(BlogList, HttpStatus.OK);
	}
	
//	@RequestMapping(value= "/blog", method = RequestMethod.POST)
//	public ResponseEntity<Void> saveBlog(@RequestBody Blog blog, UriComponentsBuilder builder)
//	{
//
//		
//		 Date date=new Date();
//		 blog.setCreatedDate(date);
//        boolean flag = blogService.saveBlog(blog);
//               if (flag == false) {
//        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//               }
//               HttpHeaders headers = new HttpHeaders();
//               headers.setLocation(builder.path("/blog{blog_id}").buildAndExpand(blog.getBlog_id()).toUri());
//               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//	}
//	
	
	@RequestMapping(value= "/blog", method = RequestMethod.POST)
	public ResponseEntity<Void> saveBlog(@RequestBody Blog blog, UriComponentsBuilder builder)
	{

		
		 Date date=new Date();
		 blog.setCreatedDate(date);
        boolean flag = blogService.saveBlog(blog);
               if (flag == true) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/blog{blog_id}").buildAndExpand(blog.getBlog_id()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	
	
	
	@RequestMapping(value="/blog/{blog_id}", method = RequestMethod.PUT )
	public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
		blogService.updateBlog(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@RequestMapping(value="/blog/{blog_id}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteBlog(@PathVariable("blog_id") Integer blog_id) {
		blogService.deleteBlog(blog_id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}
