package com.shsh.ic.model.service;

import java.util.List;

import com.shsh.ic.model.dto.FanMsg;

public interface FanMsgService {
	
	public boolean insertFanMsg(FanMsg fanMsg);
	 
	public List<?> selectFanMsgs(int playerId);

	public List<FanMsg> selectFanMsgsLatest(int playerId);

	public boolean updateFanMsgCnt(int playerId);
}
