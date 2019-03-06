package com.niit.wordoid.controller;

import java.sql.Date;
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

import com.niit.wordoid.dao.ForumDao;
import com.niit.wordoid.model.Forum;
import com.niit.wordoid.model.UserDetails;




@RestController
public class ForumController {
	
	@Autowired
	ForumDao forumDao;
	@Autowired
	Forum forum;
	@Autowired
	UserDetails userdetails;
	
	
	
	

	@RequestMapping(value="/forums", method=RequestMethod.GET)
	@ResponseBody
	
	public ResponseEntity<List<Forum>> getAllForums(){
		List<Forum> forums=forumDao.getAllForums();
		if(forums.isEmpty()){
			return new ResponseEntity<List<Forum>>(forums,HttpStatus.NO_CONTENT);
		}
		System.out.println(forums.size());
		System.out.println("fetching all forums");
		return new ResponseEntity<List<Forum>>(forums,HttpStatus.OK);
	}
	

	@RequestMapping(value="/forum/", method=RequestMethod.POST)
	public ResponseEntity<Forum> createForum(@RequestBody Forum forums,HttpSession session){
		try
		{
		UserDetails user=(UserDetails) session.getAttribute("loggedInUser");
		
		if(((user==null)||
			(forumDao.getForumByForumId(forum.getForumId())!=null)))
		{
			
			forum.setErrorCode("404");
			forum.setErrorMessage("Forum Not Created");
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}
		forum.setForumCreationDate(new Date(System.currentTimeMillis()));
		forum.setForumStatus("Pending");
		forum.setUserId(user.getUserId());
		forum.setUserName(user.getName());
		forumDao.saveForum(forum);
		System.out.println(forum.getForumId());
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}
		catch(NullPointerException e)
		{
			forum.setErrorCode("404");
			forum.setErrorMessage("User Not logged in");
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
			
		}
		}
	

	@RequestMapping(value="/forum/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Forum> updateForum(@PathVariable("id") int id, @RequestBody Forum forum,HttpSession session){
		if( ((UserDetails)session.getAttribute("loggedInUser")).getUserId()!= forum.getUserId()||(forumDao.getForumByForumId(forum.getForumId())==null)){
			forum =new Forum();
			forum.setErrorMessage("Forum does not exist with id : " +forum.getForumId());
			return new ResponseEntity<Forum>(forum, HttpStatus.NO_CONTENT);
		}
		forum.setForumCreationDate ((java.sql.Date) new Date(System.currentTimeMillis()));
		forum.setForumStatus("Pending");
		
		forum.setUserId(userdetails.getUserId());
		forum.setUserName(userdetails.getName());
		forumDao.updateForum(forum);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/forum/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Forum>deleteForum(@PathVariable("id")int id){
		Forum forum=forumDao.getForumByForumId(id);
		if(forum == null){
			forum = new Forum();
			forum.setErrorMessage("Forum does not exist with id : " + id);
			return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
			
		}
		forumDao.deleteForum(forum);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	
	@RequestMapping(value= "/forum/{id}",method=RequestMethod.GET)
	public ResponseEntity<Forum>getAllForumsByForumId(@PathVariable("id")int id){
		Forum forum = forumDao.getForumByForumId(id);
		if (forum == null){
			forum = new Forum();
			forum.setErrorMessage("Forum does not exist with id : " + id);
				return new ResponseEntity<Forum>(forum, HttpStatus.NOT_FOUND);
				
		}
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	
	
	

	@RequestMapping(value= "/approvedForum",method=RequestMethod.GET)
	public ResponseEntity<List<Forum>>getEveryApprovedForums(){
		List<Forum> forums=forumDao.getAllApprovedForums();
		if(forums.isEmpty()){
			return new ResponseEntity<List<Forum>>(forums,HttpStatus.NO_CONTENT);
		}
		System.out.println(forums.size());
		System.out.println("kkkkkk");
		return new ResponseEntity<List<Forum>>(forums,HttpStatus.OK);
	}

	
	
	

	@RequestMapping(value="/approveForum/{forumId}", method=RequestMethod.PUT)
	ResponseEntity<Forum> approveForum(@PathVariable("forumId") int forumId,HttpSession session)
	{	
		try
		{
			Forum forum=forumDao.getForumByForumId(forumId);
			if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&
					(forum!=null))
			{
				forum.setForumStatus("Approved");
				forumDao.updateForum(forum);
						return new ResponseEntity<Forum>(forum,HttpStatus.OK);
					}
			else
			{
						forum=new Forum();
						forum.setErrorCode("404");
						forum.setErrorMessage("forum not approved");
						return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
					}
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			Forum forum=new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("admin not logged in");
			return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	
	
	
	
	@RequestMapping(value="/rejectForum/{forumId}", method=RequestMethod.PUT)
	ResponseEntity<Forum> rejecteForum(@PathVariable("forumId") int forumId,HttpSession session)
	{	
		try
		{
			Forum forum=forumDao.getForumByForumId(forumId);
			if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&
					(forum!=null)){
				forum.setForumStatus("Reject");
				forumDao.updateForum(forum);
						return new ResponseEntity<Forum>(forum,HttpStatus.OK);
					}
			else
			{
						forum=new Forum();
						forum.setErrorCode("404");
						forum.setErrorMessage("forum not rejected");
						return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
					}
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
			Forum forum=new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("admin not logged in");
			return new ResponseEntity<Forum>(forum,HttpStatus.NOT_FOUND);
		}
	}		

	
	
}
