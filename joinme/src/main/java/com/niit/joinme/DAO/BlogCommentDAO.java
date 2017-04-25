package com.niit.joinme.DAO;

import com.niit.joinme.model.BlogComment;
import java.util.List;


public interface BlogCommentDAO {

	public boolean saveBlogComment(BlogComment blogComment);
	public List<BlogComment>  listCommentsByBlogId(int blog_id);
	public void deleteBlogComment(int commentId);

}
