package com.niit.wordoid.dao;

import java.util.List;

import com.niit.wordoid.model.Blog;



public interface BlogDao {
	
public List<Blog> getAllBlogs();
	
	public boolean saveBlog(Blog blog);
	
	public boolean deleteBlog(Blog blog);
	
	public boolean updateBlog(Blog blog);
	
	public Blog getBlogByBlogId(int blogId);
	
	public List<Blog> getAllApproveBlogs();

}
