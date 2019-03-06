package com.niit.wordoid.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.wordoid.dao.BlogDao;
import com.niit.wordoid.dao.BlogLikeDao;
import com.niit.wordoid.dao.NotificationDao;
import com.niit.wordoid.model.Blog;
import com.niit.wordoid.model.BlogLike;
import com.niit.wordoid.model.UserDetails;





@RestController
public class BlogController {
	@Autowired
	BlogDao blogDao;
	@Autowired
	Blog blog;
	@Autowired
	UserDetails userdetails;
	@Autowired
	BlogLikeDao blogLikeDao;
	@Autowired
	BlogLike blogLike;
	@Autowired
	NotificationDao notificationDao;
	
	

	@RequestMapping(value="/blogs", method=RequestMethod.GET)
	@ResponseBody
	
	public ResponseEntity<List<Blog>> getAllBlogs(){
		List<Blog> blogs=blogDao.getAllBlogs();
		if(blogs.isEmpty()){
			return new ResponseEntity<List<Blog>>(blogs,HttpStatus.NO_CONTENT);
		}
		System.out.println(blogs.size());
		System.out.println("fetching all blogs");
		return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
	}
	
	

	@RequestMapping(value="/blog/", method=RequestMethod.POST)
	public ResponseEntity<Blog> creatBlog(@RequestBody Blog blog,HttpSession session){
		System.out.println("Create Blog");
		try
		{
		UserDetails user=(UserDetails) session.getAttribute("loggedInUser");
		
		if(((user==null)||
			(blogDao.getBlogByBlogId(blog.getBlogId())!=null)))
		{
			blog.setErrorCode("404");
			blog.setErrorMessage("BlogComment Not Created");
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);	
		}
		else{
		blog.setBlogDate(new Date(System.currentTimeMillis()));
		blog.setBlogStatus("Pending");
		blog.setUserId(user.getUserId());
		blogDao.saveBlog(blog);
		System.out.println("blog" + blog.getBlogId());
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		}
		catch(NullPointerException e)
		{
			blog.setErrorCode("404");
			blog.setErrorMessage("User Not logged in");
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
			
		}
		
		}
	
	
	@RequestMapping(value="/blog/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") int id, @RequestBody Blog blog){
		if(blogDao.getAllBlogs()==null){
			blog =new Blog();
			blog.setErrorMessage("Blog does not exist with id : " +blog.getBlogId());
			return new ResponseEntity<Blog>(blog, HttpStatus.NO_CONTENT);
		}
		blog.setBlogStatus("Pending");
	    blog.setBlogDate(new Date(System.currentTimeMillis()));
	    blog.setUserId(userdetails.getUserId());
		blogDao.updateBlog(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/blog/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Blog>deleteBlog(@PathVariable("id")int id){
		Blog blog=blogDao.getBlogByBlogId(id);
		if(blog == null){
			blog = new Blog();
			blog.setErrorMessage("Blog does not exist with id : " + id);
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			
		}
		blogDao.deleteBlog(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value= "/blog/{id}",method=RequestMethod.GET)
	public ResponseEntity<Blog>getAllBlogsByBlogId(@PathVariable("id")int id){
		Blog blog = blogDao.getBlogByBlogId(id);
		if (blog == null){
			blog = new Blog();
			blog.setErrorMessage("Blog does not exist with id : " + id);
				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
				
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value= "/approvedBlog",method=RequestMethod.GET)
	public ResponseEntity<List<Blog>>getAllApprovedBlogs(){
		List<Blog> blogs=blogDao.getAllApproveBlogs();
		if(blogs.isEmpty()){
			return new ResponseEntity<List<Blog>>(blogs,HttpStatus.NO_CONTENT);
		}
		System.out.println(blogs.size());
		System.out.println("approved blogs displaying");
		return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
	}
	

	@RequestMapping(value="/approveBlog/{blogId}", method=RequestMethod.PUT)
	ResponseEntity<Blog>approveBlog(@PathVariable("blogId") int blogId,HttpSession session)
	{	
		try
		{
			Blog blog=blogDao.getBlogByBlogId(blogId);
			if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&
					(blog!=null))
			{
						blog.setBlogStatus("Approved");
						blogDao.updateBlog(blog);
						return new ResponseEntity<Blog>(blog,HttpStatus.OK);
					}
			else
			{
						blog=new Blog();
						blog.setErrorCode("404");
						blog.setErrorMessage("blog not approved");
						return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
					}
			
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			Blog blog=new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("admin not loggedin");
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
	}
	
	


	@RequestMapping(value="/rejectBlog/{blogId}", method=RequestMethod.PUT)
	ResponseEntity<Blog>rejectBlog(@PathVariable("blogId") int blogId,HttpSession session)
	{	
		try
		{
			Blog blog=blogDao.getBlogByBlogId(blogId);
			if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&
					(blog!=null))
			{
						blog.setBlogStatus("Rejected");
						blog.setBlogCountLike(blog.getBlogCountLike());
						blog.setBlogCommentCount(blog.getBlogCommentCount());
						blog.setUserId(blog.getUserId());
						blogDao.updateBlog(blog);
						return new ResponseEntity<Blog>(blog,HttpStatus.OK);
					}
			else
			{
						blog=new Blog();
						blog.setErrorCode("404");
						blog.setErrorMessage("blog not rejected");
						return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
					}
			
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			Blog blog=new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("admin not loggedin");
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	
	@RequestMapping(value="/likeBlog/{blogId}", method=RequestMethod.PUT)
	public ResponseEntity<Blog> likeBlog(@PathVariable("blogId") int blogId,HttpSession session) 
	{
		try
		{
			UserDetails user=(UserDetails) session.getAttribute("loggedInUser");
			System.out.println(user.getName());
			Blog blog = blogDao.getBlogByBlogId(blogId);
			if (blog == null)
			{
				blog = new Blog();
				
				blog.setErrorMessage("No blog exist with id : " + blogId);
	
				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		}
		
			else if(blogLikeDao.isExist(blogId, user.getUserId()))
			{
				blog = new Blog();
				
				blog.setErrorMessage("User has already liked the blog: " + blogId);

				return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
			}
			
		blog.setBlogCountLike(blog.getBlogCountLike()+1);
		blogDao.updateBlog(blog);
		BlogLike blogLike=new BlogLike();
		blogLike.setBlogId(blogId);blogLike.setUserId(user.getUserId());blogLike.setUserName(user.getName());
		blogLike.setBlogLikeDate(new Date(System.currentTimeMillis()));
		blogLikeDao.saveBlogLike(blogLike);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			Blog blog=new Blog();
			blog.setErrorMessage(" user not logged in");
			blog.setErrorCode("404");
			return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);

		}
		
		
	}	

	
	
	
	

	@RequestMapping(value="/likeBlog/{blogId}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<BlogLike>> getBlogLikesByblogId(@PathVariable("blogId") int blogId,HttpSession session) {
		Blog blog = blogDao.getBlogByBlogId(blogId);
		if (blog == null) {
			return new ResponseEntity<List<BlogLike>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BlogLike>>(blogLikeDao.getBlogLikesByBlogId(blogId), HttpStatus.OK);
		
	}
	
}
