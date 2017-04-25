package com.niit.joinme.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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


import com.niit.joinme.model.BlogLike;
import com.niit.joinme.service.BlogLikeService;

@RestController
public class BlogLikeController {

	@Autowired
	private BlogLikeService blogLikeService;
	

	@RequestMapping(value="/blogLike", method = RequestMethod.GET )
	public ResponseEntity<List<BlogLike>> listAllLikesByBlogId(Integer blog_id,HttpSession session) {
		

		blog_id=(Integer) session.getAttribute("blog_id");


		 System.out.println("list like blog_id....."+blog_id);
		List<BlogLike> like = blogLikeService.listAllLikesByBlogId(blog_id);
		return new ResponseEntity<List<BlogLike>>(like, HttpStatus.OK);
	}
	
	
	@RequestMapping(value= "/blogLike/", method = RequestMethod.POST)
	public ResponseEntity<Void> addBlogLike(@RequestBody BlogLike blogLike, UriComponentsBuilder builder,HttpSession session,Integer blog_id)
	{
          

		blogLike.setUser_id(2);
    System.out.println("user id is ...");
   
	     blog_id=(Integer) session.getAttribute("blog_id");
  
    
	      System.out.println("like blog id is..... :"+blog_id);
	      blogLike.setBlog_id(blog_id);

        boolean flag = blogLikeService.addBlogLike(blogLike);
               if (flag == true) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/blogblogLike/{blogLikeId}").buildAndExpand(blogLike.getBlogLikeId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	

	

	
	@RequestMapping(value="/blogLike/{blogLikeId}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteBlogComment(@PathVariable("blogLikeId") Integer blogLikeId) {
		blogLikeService.deleteBlogLike(blogLikeId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	

}
