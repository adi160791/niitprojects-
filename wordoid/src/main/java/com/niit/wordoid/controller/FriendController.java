package com.niit.wordoid.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.wordoid.dao.FriendDao;
import com.niit.wordoid.dao.UserDetailsDao;
import com.niit.wordoid.model.Friend;
import com.niit.wordoid.model.UserDetails;



@RestController
public class FriendController {
	@Autowired
	Friend friend;
	
	@Autowired
	FriendDao friendDao;
	
	@Autowired
	UserDetailsDao userdetailsDao;
	
			
	@RequestMapping(value = "/myFriends" , method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Friend>>getMyFriends(HttpSession session) {
		System.out.println("**********Starting of myFriends() method");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		System.out.println("Friends"+loggedInUser.getUserId()); // A= for approved friends and R= for reject U= unfriend
		List<Friend> myFriends = friendDao.getMyFriends(loggedInUser.getUserId());
		System.out.println("**********End of myFriends() method");
		return new ResponseEntity<List<Friend>> (myFriends, HttpStatus.OK);
	}
													
	
	@RequestMapping(value = "/addFriend/{friendId}" , method=RequestMethod.POST)			
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendId") String friendId, HttpSession session) {
		System.out.println("**********Starting of sendFriendRequest() method");
		Friend friend = new Friend();
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		
		Friend f=friendDao.get(loggedInUser.getUserId(), friendId);
		
		System.out.println(loggedInUser.getUserId()+"=======++++   "+friendId);
		if(f==null)
		{
		friend.setUserId(loggedInUser.getUserId());
		
		friend.setFriendId(friendId);
		friend.setStatus("N");	// N = New, A = Accepted, R = Rejected, U = Unfriend 
		friendDao.save(friend);
		}
		
		else
		{
			f.setStatus("N");
			friendDao.update(f);
		}
		System.out.println("**********End of sendFriendRequest() method");
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/unFriend/{friendId}" , method=RequestMethod.PUT)			
	public ResponseEntity<Friend> unFriend(@PathVariable("friendId") String friendId, HttpSession session) {
		System.out.println("**********Starting of unFriend() method");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		String userId=loggedInUser.getUserId();
		Friend friend = friendDao.get(userId, friendId);
		friend.setUserId(loggedInUser.getUserId());
		friend.setFriendId(friendId);
		friend.setStatus("U");	// N = New, A = Accepted, R = Rejected, U = Unfriend 
		friendDao.update(friend);
		System.out.println("**********End of unFriend() method");
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	
	@RequestMapping(value = "user/rejectFriend/{friendId}"  , method=RequestMethod.PUT)				
	public ResponseEntity<Friend> rejectFriendRequest(@PathVariable("friendId") String friendId, HttpSession session) {
		System.out.println("**********Starting of rejectFriendRequest() method");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		String userId=loggedInUser.getUserId();
		Friend friend = friendDao.get(userId, friendId);
		friend.setUserId(loggedInUser.getUserId());
		friend.setFriendId(friendId);
		friend.setStatus("R");	// N = New, A = Accepted, R = Rejected, U = Unfriend  
		friendDao.update(friend);
		System.out.println("**********End of rejectFriendRequest() method");
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "user/acceptFriend/{friendId}"  , method=RequestMethod.PUT)			
	public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("friendId") String friendId, HttpSession session) {
		System.out.println("**********Starting of acceptFriendRequest() method");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		String userId=loggedInUser.getUserId();
		System.out.println("iiiiii" + userId );
		Friend friend = friendDao.getRequest(userId, friendId);
		System.out.println("ppppp "+ friend);
		friend.setUserId(loggedInUser.getUserId());
		System.out.println("bbbbbb");
		friend.setFriendId(userId);
		friend.setUserId(friendId);
		friend.setStatus("A");	// N = New, A = Accepted, R = Rejected, U = Unfriend 
		friendDao.update(friend);
		System.out.println("**********End of acceptFriendRequest() method");
		return new ResponseEntity<Friend> (friend, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/newFriendRequests",method=RequestMethod.GET )	
	
	public ResponseEntity<List<Friend>> newFriendRequests(HttpSession session) {
		System.out.println("**********Starting of listFriends() method.");
		UserDetails loggedInUser = (UserDetails) session.getAttribute("loggedInUser");
		List<Friend> friend = friendDao.getNewFriendRequests(loggedInUser.getUserId());
		if(friend.isEmpty()) {
			return new ResponseEntity<List<Friend>>(HttpStatus.NO_CONTENT);
		}
		System.out.println("**********End of listFriends() method.");
		return new ResponseEntity<List<Friend>>(friend, HttpStatus.OK);
	}
	
	


}
	
	
	
	
	




