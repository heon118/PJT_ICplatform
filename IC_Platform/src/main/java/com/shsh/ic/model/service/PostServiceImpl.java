package com.shsh.ic.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsh.ic.model.dao.PostDao;
import com.shsh.ic.model.dto.Posts;

@Service
public class PostServiceImpl implements PostService {

	private PostDao postDao;

	@Autowired
	public PostServiceImpl(PostDao postDao) {
		this.postDao = postDao;
	}

	@Override
	public boolean insertPost(Posts post, int playerId) {
		return postDao.insertPost(post) > 0;
	}

	@Override
	public List<Posts> selectPosts(int playerId) {
		return postDao.selectPosts(playerId);
	}

	@Override
	public boolean deletePost(int postId) {
		return postDao.deletePost(postId) > 0;
	}

	@Override
	public Posts selectPostOne(int postId) {
		return postDao.selectPostOne(postId);
	}

}
