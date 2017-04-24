package com.niit.joinme.controller;

import java.util.Date;
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
import com.niit.joinme.model.BlogComment;
import com.niit.joinme.service.BlogCommentService;


@RestController
public class BlogCommentController 
{
	@Autowired
	private BlogCommentService blogCommentService;
	

	
//	@RequestMapping(value= "/blogComment", method = RequestMethod.GET)
//	public ResponseEntity<List<BlogComment>> listCommentsByBlogId(HttpSession session) {
//		
//		session.setAttribute("blog_id",blogComment.getBlog_id());
//		session.getAttribute("blog_id");           
//		
//		List<BlogComment> BlogCommentList = blogCommentService.listCommentsByBlogId();
//		return new ResponseEntity<List<BlogComment>>(BlogCommentList, HttpStatus.OK);
//	}
	

	@RequestMapping(value="/blogComment/{blog_id}", method = RequestMethod.GET )
	public ResponseEntity<List<BlogComment>> listCommentsByBlogId(@PathVariable("blog_id") Integer blog_id,HttpSession session,BlogComment blogComment) {
		
		session.setAttribute("blog_id",blogComment.getBlog_id());
		session.getAttribute("blog_id"); 
		
		List<BlogComment> comments = blogCommentService.listCommentsByBlogId(blog_id);
		return new ResponseEntity<List<BlogComment>>(comments, HttpStatus.OK);
	}
	
	
	@RequestMapping(value= "/blogComment", method = RequestMethod.POST)
	public ResponseEntity<Void> addBlog(@RequestBody BlogComment blogComment, UriComponentsBuilder builder)
	{

		
		 Date commentDate=new Date();
		blogComment.setCommentDate(commentDate);
        boolean flag = blogCommentService.addBlog(blogComment);
               if (flag == true) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/blogComment{blogCommentId}").buildAndExpand(blogComment.getBlogComment()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/blogComment/{blogCommentId}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteBlogComment(@PathVariable("blogCommentId") Integer blogCommentId) {
		blogCommentService.deleteBlogComment(blogCommentId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	

}
