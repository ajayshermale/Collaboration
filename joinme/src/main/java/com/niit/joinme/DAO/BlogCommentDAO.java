package com.niit.joinme.DAO;

import java.util.List;

import com.niit.joinme.model.BlogComment;

public interface BlogCommentDAO {

	public boolean addBlog(BlogComment blogComment);
	public List<BlogComment> listCommentsByBlogId(int blog_id);
	public void deleteBlogComment(int commentId);
	

}
