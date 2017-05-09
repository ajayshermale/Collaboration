package com.niit.joinme.DAO;
import java.util.List;

import com.niit.joinme.model.BlogLike;



public interface BlogLikeDAO {

	public boolean addBlogLike(BlogLike blogLike);
	public List<BlogLike> listAllLikesByBlogId(int blog_id);
	public void deleteBlogLike(int blogLikeId);

}
