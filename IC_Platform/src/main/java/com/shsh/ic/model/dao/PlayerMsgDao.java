package com.shsh.ic.model.dao;

import java.util.List;

import com.shsh.ic.model.dto.Player;
import com.shsh.ic.model.dto.PlayerMsg;

public interface PlayerMsgDao {
	// 소통 메시지 작성
	 public int insertPlayerMsg(PlayerMsg playermsg);
	 
	 // 소통 메시지 가져오기
	 public List<PlayerMsg> selectPlayerMsg(int playerId);
}
