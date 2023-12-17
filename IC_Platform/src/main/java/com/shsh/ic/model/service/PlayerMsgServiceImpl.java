package com.shsh.ic.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsh.ic.model.dao.PlayerMsgDao;
import com.shsh.ic.model.dto.PlayerMsg;

@Service
public class PlayerMsgServiceImpl implements PlayerMsgService {
	
	private PlayerMsgDao playermsgdao;
	
	@Autowired
	public PlayerMsgServiceImpl(PlayerMsgDao playermsgdao) {
		this.playermsgdao = playermsgdao;
	}

	@Override
	public boolean insertPlayerMsg(PlayerMsg playermsg) {
		return playermsgdao.insertPlayerMsg(playermsg) > 0;
	}

	@Override
	public List<PlayerMsg> selectPlayerMsg(int playerId) {
		return playermsgdao.selectPlayerMsg(playerId);
	}
}
