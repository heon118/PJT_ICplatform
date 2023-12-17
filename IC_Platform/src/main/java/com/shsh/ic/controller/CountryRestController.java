package com.shsh.ic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shsh.ic.model.dto.Country;
import com.shsh.ic.model.service.CountryService;

@RestController
@RequestMapping("/api")
public class CountryRestController {

	private CountryService countryService;
	
	@Autowired
	public CountryRestController(CountryService countryService) {
		this.countryService = countryService;
	}
	
	// 전체 메달 가져오기
	@GetMapping("/countries")
	public ResponseEntity<?> getallCountry(){
		List<Country> countryList = countryService.selectCountries();
		
		if(countryList == null || countryList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Country>>(countryList, HttpStatus.OK);
	}
	
	// 금메달 추가
	@PutMapping("/countries/{name}/gold")
	public ResponseEntity<?> countryGold(@PathVariable String name){
		if(countryService.updateCountryGold(name))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	// 은메달 추가
	@PutMapping("/countries/{name}/silver")
	public ResponseEntity<?> CountrySilver(@PathVariable String name){
		if(countryService.updateCountrySilver(name))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	// 동메달 추가
	@PutMapping("/countries/{name}/bronze")
	public ResponseEntity<?> CountryBronze(@PathVariable String name){
		if(countryService.updateCountryBronze(name))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
}
