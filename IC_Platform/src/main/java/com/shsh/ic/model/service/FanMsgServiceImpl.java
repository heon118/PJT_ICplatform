package com.shsh.ic.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsh.ic.model.dao.FanMsgDao;
import com.shsh.ic.model.dto.FanMsg;

@Service
public class FanMsgServiceImpl implements FanMsgService {

	private FanMsgDao fanmsgdao;
	
	@Autowired
	public FanMsgServiceImpl(FanMsgDao fanmsgdao) {
		this.fanmsgdao = fanmsgdao;
	}
	
	@Override
	public boolean insertFanMsg(FanMsg fanMsg) {
		return fanmsgdao.insertFanMsg(fanMsg) > 0;
	}

	@Override
	public List<?> selectFanMsgs(int playerId) {
		return fanmsgdao.selectFanMsgs(playerId);
	}

	@Override
	public List<FanMsg> selectFanMsgsLatest(int playerId) {
		return fanmsgdao.selectFanMsgsLatest(playerId);
	}

	@Override
	public boolean updateFanMsgCnt(int playerId) {
		return fanmsgdao.updateFanMsgCnt(playerId) > 0;
	}

}
