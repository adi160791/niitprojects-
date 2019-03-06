package com.niit.wordoid.dao;

import java.util.List;

import com.niit.wordoid.model.BlogLike;

public interface BlogLikeDao {

	public List<BlogLike>getBlogLikesByBlogId(int blogId);
	public boolean saveBlogLike(BlogLike blogLike);
	public boolean isExist(int blogId,String userId);
}
