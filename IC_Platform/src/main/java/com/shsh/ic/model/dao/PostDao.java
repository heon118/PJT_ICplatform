package com.shsh.ic.model.dao;

import java.util.List;

import com.shsh.ic.model.dto.Posts;

public interface PostDao {
	// 해당 선수가 등록한 이미지들 가져오기
	public List<Posts> selectPosts(int playerId);

	// 선수 이미지 등록
	public int insertPost(Posts post);
	
	// 선수 이미지 삭제
	public int deletePost(int postId);
	
	// 선수 이미지 가져오기(삭제를 위함)
	public Posts selectPostOne(int postId);
}
