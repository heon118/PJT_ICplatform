package com.shsh.ic.model.dao;

import java.util.List;

import com.shsh.ic.model.dto.FanMsg;

public interface FanMsgDao {
	// 팬 응원 메시지 작성
	public int insertFanMsg(FanMsg fanMsg);

	// 팬 응원 메시지 가져오기
	public List<?> selectFanMsgs(int playerId);

	// 팬 응원 메시지 가져오기
	public List<FanMsg> selectFanMsgsLatest(int playerId);

	// 응원 메시지 작성 시 메시지 수 증가
	public int updateFanMsgCnt(int playerId);
}
