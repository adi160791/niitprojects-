package com.niit.wordoid.dao;

import java.util.List;

import com.niit.wordoid.model.Forum;



public interface ForumDao {
	
public boolean saveForum(Forum forum);
	
	public boolean deleteForum(Forum forum);
	
	public boolean updateForum(Forum forum);
	
	public Forum getForumByForumId(int forumId);
	
	public List<Forum> getAllForums();
	
	public List<Forum> getAllApprovedForums();

}
