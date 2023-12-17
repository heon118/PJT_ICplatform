package com.shsh.ic.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsh.ic.model.dao.PlayerDao;
import com.shsh.ic.model.dto.Player;

@Service
public class PlayerServiceImpl implements PlayerService {

	private PlayerDao playerDao;
	
	@Autowired
	public PlayerServiceImpl(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}
	
	@Override
	public List<Player> selectAllPlayersName() {
		return playerDao.selectAllPlayersName();
	}

	@Override
	public List<Player> selectAllPlayersPopular() {
		return playerDao.selectAllPlayersPopular();
	}

	@Override
	public List<Player> selectSportsPlayers(String sports) {
		return playerDao.selectSportsPlayers(sports);
	}
	
	@Override
	public List<Player> selectTodayPlayers() {
		return playerDao.selectTodayPlayers();
	}
	
	@Override
	public List<String> selectSports() {
		return playerDao.selectSports();
	}

	@Override
	public Player selectPlayerDetail(int id) {
		return playerDao.selectPlayerDetail(id);
	}

	@Override
	public boolean updatePlayerIntro(Player player) {
		return playerDao.updatePlayerIntro(player) > 0;
	}

	@Override
	public boolean updatePlayerGold(int id) {
		return playerDao.updatePlayerGold(id) > 0;
	}

	@Override
	public boolean updatePlayerSilver(int id) {
		return playerDao.updatePlayerSilver(id) > 0;
	}

	@Override
	public boolean updatePlayerBronze(int id) {
		return playerDao.updatePlayerBronze(id) > 0;
	}

}
