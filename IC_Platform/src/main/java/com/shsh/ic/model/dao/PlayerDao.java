package com.shsh.ic.model.dao;

import java.util.List;

import com.shsh.ic.model.dto.Player;

public interface PlayerDao {

	// 전체 선수 이름 가나다순 정렬
	public List<Player> selectAllPlayersName();
	
	//전체 선수 인기순(메시지 수) 정렬
	public List<Player> selectAllPlayersPopular();
	
	// 종목별 선수 목록
	public List<Player> selectSportsPlayers(String sports);
	
	// 오늘 출전 선수
	public List<Player> selectTodayPlayers();
	
	// 종목 정렬
	public List<String> selectSports();
	
	// id로 선수 선택
	public Player selectPlayerDetail(int id);
	
	// 선수 상태메시지 변경
	public int updatePlayerIntro(Player player);
	
	// 메달 수 편집
	public int updatePlayerGold(int id);
	public int updatePlayerSilver(int id);
	public int updatePlayerBronze(int id);
	
}
