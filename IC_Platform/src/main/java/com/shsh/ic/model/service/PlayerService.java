package com.shsh.ic.model.service;

import java.util.List;

import com.shsh.ic.model.dto.Player;

public interface PlayerService {

	public List<Player> selectAllPlayersName();

	public List<Player> selectAllPlayersPopular();

	public List<Player> selectSportsPlayers(String sports);

	public List<Player> selectTodayPlayers();
	
	public List<String> selectSports();

	public Player selectPlayerDetail(int id);
	
	public boolean updatePlayerIntro(Player player);

	public boolean updatePlayerGold(int id);

	public boolean updatePlayerSilver(int id);

	public boolean updatePlayerBronze(int id);

}
