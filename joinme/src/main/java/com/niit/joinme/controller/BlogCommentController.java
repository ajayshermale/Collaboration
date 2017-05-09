package com.niit.joinme.controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.niit.joinme.model.BlogComment;
import com.niit.joinme.service.BlogCommentService;
import com.niit.joinme.service.UserService;
@RestController
public class BlogCommentController {
	@Autowired
	private BlogCommentService blogCommentService;
	@Autowired
	private UserService userService;

	@RequestMapping(value="/blogComment", method = RequestMethod.GET )
	public ResponseEntity<List<BlogComment>> listCommentsByBlogId(Integer blog_id,HttpSession session,BlogComment blogComment) {


		blog_id=(Integer) session.getAttribute("blog_id");

//		session.setAttribute("blog_id",blogComment.getBlogId());
//		session.getAttribute("blog_id"); 
		
//		session.setAttribute("user_id",blogComment.getUserId());
//		session.getAttribute("user_id"); 
		 System.out.println("blogId....."+blog_id);
		List<BlogComment> comments = blogCommentService.listCommentsByBlogId(blog_id);
		return new ResponseEntity<List<BlogComment>>(comments, HttpStatus.OK);
	}
	
	
	@RequestMapping(value= "/blogComment/", method = RequestMethod.POST)
	public ResponseEntity<Void> saveBlogComment(@RequestBody BlogComment blogComment, UriComponentsBuilder builder,HttpSession session,Integer blog_id)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      String userName = authentication.getName();
	      int user_id = userService.loadUserByUsername(userName).getUserId();
	      blogComment.setUserId(user_id);   
       

   // blogComment.setUserId(175);
    System.out.println("user id is ...");

	     blog_id=(Integer) session.getAttribute("blog_id");
    


	      System.out.println("blog id is..... :"+blog_id);
	      blogComment.setBlogId(blog_id);;
		 Date commentDate=new Date();
		blogComment.setCommentDate(commentDate);
        boolean flag = blogCommentService.saveBlogComment(blogComment);
               if (flag == true) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/blogblogComment/{commentId}").buildAndExpand(blogComment.getCommentId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	
	
	@RequestMapping(value="/blogComment/{blogCommentId}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteBlogComment(@PathVariable("blogCommentId") Integer blogCommentId) {
		blogCommentService.deleteBlogComment(blogCommentId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	


}
