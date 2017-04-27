package com.niit.joinme.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.util.UriComponentsBuilder;

import com.niit.joinme.model.Blog;
import com.niit.joinme.service.BlogService;

@RestController
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value="/blog/{blog_id}", method = RequestMethod.GET )
	public ResponseEntity<Blog> getById(@PathVariable("blog_id") Integer blog_id,HttpSession session) {
		session.setAttribute("blog_id", blog_id);
	    System.out.println("set blog id..."+blog_id);
		Blog blog = blogService.getById(blog_id);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/blog", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getAllBlogs() {
		
		  
		List<Blog> BlogList = blogService.getAllBlogs();
		return new ResponseEntity<List<Blog>>(BlogList, HttpStatus.OK);
	}
	

	@RequestMapping(value= "/blog", method = RequestMethod.POST)
	
	public ResponseEntity<Void> saveBlog(@RequestBody Blog blog,Integer blog_id,UriComponentsBuilder builder,HttpSession session)
	{
		
		 Date date=new Date();
		 blog.setCreatedDate(date);
        boolean flag = blogService.saveBlog(blog);
       
               if (flag == true) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
            
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/blog{blog_id}").buildAndExpand(blog.getBlog_id()).toUri());
               session.setAttribute("blog_id",blog.getBlog_id());
        		 System.out.println("blog id to set "+blog.getBlog_id());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
              
	}
	
	//blog image upload
		@RequestMapping(value= "/blog/blogImage",headers=("content-type=multipart/*"),method = RequestMethod.POST)
		public ResponseEntity<Void> addblogImage(@RequestParam(value="file")MultipartFile file,HttpSession session,UriComponentsBuilder builder,Blog blog){
		
			    
      String path="C:\\Users\\Rahul\\workspace project2 new\\joinme_frontend\\src\\main\\webapp\\resource\\images\\";
      int	blog_id=(Integer) session.getAttribute("blog_id");
		path=path+blog_id+".jpg";
		System.out.println("blog idd is"+blog_id);
		File f=new File(path);
		System.out.println("path is"+path);
		
		//MultipartFile filedet=blog.getBlogImage();
		
		if(!file.isEmpty())
		{
			try
			{
			  byte[] bytes=file.getBytes();
			  FileOutputStream fos=new FileOutputStream(f);
            			BufferedOutputStream bs=new BufferedOutputStream(fos);
            			bs.write(bytes);
            			bs.close();
           			 System.out.println("File Uploaded Successfully");
			}
			catch(Exception e)
			{
				System.out.println("Exception Arised"+e);
			}
		}
		else
		{
			System.out.println("File is Empty");
		}
	     		
	     		 HttpHeaders headers = new HttpHeaders();
	             headers.setLocation(builder.path("/blog/{blog_id}").buildAndExpand(blog.getBlog_id()).toUri());
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
