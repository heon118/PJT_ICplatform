package com.shsh.ic.model.dao;

import java.util.List;

import com.shsh.ic.model.dto.Country;

public interface CountryDao {
	// 국가 정렬
	public List<Country> selectCountries();
	
	// 국가별 메달 수 편집
	public int updateCountryGold(String name);
	public int updateCountrySilver(String name);
	public int updateCountryBronze(String name);
}
