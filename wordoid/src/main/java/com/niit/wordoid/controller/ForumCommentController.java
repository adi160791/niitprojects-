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

import com.niit.wordoid.dao.ForumCommentDao;
import com.niit.wordoid.dao.ForumDao;
import com.niit.wordoid.model.BlogComment;
import com.niit.wordoid.model.Forum;
import com.niit.wordoid.model.ForumComment;
import com.niit.wordoid.model.UserDetails;

@RestController
public class ForumCommentController {
    @Autowired
	ForumCommentDao forumCommentDao;
	@Autowired
	ForumComment forumComment;
	@Autowired
	Forum forum;
	@Autowired
	ForumDao forumDao;
	
	
	
	@RequestMapping(value="/forumComments", method=RequestMethod.GET)
	@ResponseBody
	
	public ResponseEntity<List<ForumComment>> getAllForumComment(){
		List<ForumComment> forums=forumCommentDao.getAllForumComments();
		if(forums.isEmpty()){
			forumComment.setErrorMessage("ForumComment does not exist at all" );
			return new ResponseEntity<List<ForumComment>>(forums,HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<ForumComment>>(forums,HttpStatus.OK);
	}
	
	@RequestMapping(value= "/forumComment/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<ForumComment>> getAllForumCommentsByUserId(@PathVariable("id")String id){
		List<ForumComment> forumComments=forumCommentDao.listByUserId(id);
		if(forumComments.isEmpty()){
			forumComment.setErrorMessage("Forum does not exist with id : " +id);
			return new ResponseEntity<List<ForumComment>>(forumComments,HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<ForumComment>>(forumComments,HttpStatus.OK);
	}
	

	@RequestMapping(value="/saveForumComment/", method=RequestMethod.POST)
	public ResponseEntity<ForumComment> creatForumComment(@RequestBody ForumComment forumComment , HttpSession session){
		    UserDetails user=(UserDetails) session.getAttribute("loggedInUser");
		   forumComment.setForumCommentDate(new Date());
		    forumComment.setUserId(user.getUserId());
			forumComment.setUserName(user.getName());
			forumCommentDao.save(forumComment);
			return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
		
	}	
	
	@RequestMapping(value="/updateForumComment/{id}", method=RequestMethod.PUT)
public ResponseEntity<ForumComment> updateForumComment(@PathVariable("id") int id, @RequestBody ForumComment forumComment,HttpSession session){
	UserDetails user=(UserDetails) session.getAttribute("loggedInUser");
	if(((UserDetails)session.getAttribute("loggedInUser")).getUserId()!=forumComment.getUserId()||(forumCommentDao.getByForumCommentId(forumComment.getForumCommentId()) == null)){
		forumComment =new ForumComment();
		forumComment.setErrorMessage("Forum does not exist with id : " +id);
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NO_CONTENT);
	}
	Date forumCommentDate=new Date(System.currentTimeMillis());
		
		forumComment.setForumCommentDate(forumCommentDate);
		forumComment.setUserId(user.getUserId());
		forumComment.setUserName(user.getName());
	forumCommentDao.update(forumComment);
	return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
}	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/deleteForumComment/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<ForumComment>deleteForumComment(@PathVariable("id")int id,HttpSession session){
		ForumComment forumComment=forumCommentDao.getByForumCommentId(id);
		if(((UserDetails)session.getAttribute("loggedInUser")).getUserId()!= forumComment.getUserId()||(forumComment == null)){
			forumComment = new ForumComment();
			forumComment.setErrorMessage("ForumComment does not exist with forumComment id : " + id);
			return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
			
		}
		forumCommentDao.delete(forumComment);
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value= "/forumCommentById/{id}",method=RequestMethod.GET)
	public ResponseEntity<ForumComment>getAllForumsByForumCommentId(@PathVariable("id")int id){
		ForumComment forumComment = forumCommentDao.getByForumCommentId(id);
		if (forumComment == null){
			forumComment = new ForumComment();
			forumComment.setErrorMessage("ForumComment does not exist with id : " + id);
				return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
				
		}
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}
	
	
	
	
	
	
  @RequestMapping(value= "/forumCommentByforumId/{id}", method=RequestMethod.GET)
	
	public ResponseEntity<List<ForumComment>> getAllForumComments(@PathVariable("id")int id){
		List<ForumComment> forumComments=forumCommentDao.listByForumId(id);
		if(forumComments.isEmpty()){
			forumComment.setErrorMessage("forumComment does not exist with id : " +id);
			return new ResponseEntity<List<ForumComment>>(forumComments,HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<ForumComment>>(forumComments,HttpStatus.OK);
	}
  
}
