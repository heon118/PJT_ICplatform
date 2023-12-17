package com.shsh.ic.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shsh.ic.model.dto.Posts;

public interface PostService {

	public boolean insertPost(Posts post, int playerId);

	public List<Posts> selectPosts(int playerId);
	
	public boolean deletePost(int postId);
	
	public Posts selectPostOne(int postId);
}
