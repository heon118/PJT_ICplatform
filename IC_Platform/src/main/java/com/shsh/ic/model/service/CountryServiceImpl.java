package com.shsh.ic.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsh.ic.model.dao.CountryDao;
import com.shsh.ic.model.dto.Country;

@Service
public class CountryServiceImpl implements CountryService {

	private CountryDao countryDao;
	
	@Autowired
	public CountryServiceImpl(CountryDao countryDao) {
		this.countryDao = countryDao;
	}
	
	@Override
	public List<Country> selectCountries() {
		return countryDao.selectCountries();
	}

	@Override
	public boolean updateCountryGold(String name) {
		return countryDao.updateCountryGold(name) > 0;
	}

	@Override
	public boolean updateCountrySilver(String name) {
		return countryDao.updateCountrySilver(name) > 0;
	}

	@Override
	public boolean updateCountryBronze(String name) {
		return countryDao.updateCountryBronze(name) > 0;
	}
}
