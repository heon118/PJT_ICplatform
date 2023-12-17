package com.shsh.ic.model.service;

import java.util.List;

import com.shsh.ic.model.dto.Player;
import com.shsh.ic.model.dto.PlayerMsg;

public interface PlayerMsgService {
	
	 public boolean insertPlayerMsg(PlayerMsg playermsg);
	 
	 public List<PlayerMsg> selectPlayerMsg(int playerId);
}
