package com.shsh.ic.model.service;

import java.util.List;

import com.shsh.ic.model.dto.Country;

public interface CountryService {
	public List<Country> selectCountries();
	
	public boolean updateCountryGold(String name);
	public boolean updateCountrySilver(String name);
	public boolean updateCountryBronze(String name);
}