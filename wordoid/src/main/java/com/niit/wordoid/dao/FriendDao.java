package com.niit.wordoid.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.wordoid.model.Friend;

@Repository
public interface FriendDao {
	public boolean save(Friend friend);
	
	public boolean update(Friend friend);					
			
	public Friend get(String userId, String friendId);	
	public Friend getRequest(String userId, String friendId);
	public List<Friend> getMyFriends(String userId);	
	
	public List<Friend> getNewFriendRequests(String friendId);
	
	public void setOnline(String userId);
	
	public void setOffline(String userId);	
	
	public boolean isFriendtrue(String userId, String friendId);



}
