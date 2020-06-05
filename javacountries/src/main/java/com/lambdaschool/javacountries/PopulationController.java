package com.lambdaschool.javacountries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/population")
public class PopulationController {
	@GetMapping(value = "/size/{peopleNum}", produces = {"application/json"})
	public ResponseEntity<?> getCountriesByPopSize(@PathVariable long peopleNum) {
		List<Country> filteredList = JavaCountriesApplication.countryData.findCountries(c -> c.getPopulation() >= peopleNum);
		filteredList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
		return new ResponseEntity<>(filteredList, HttpStatus.OK);
	}

	@GetMapping(value = "/min", produces = {"application/json"})
	public ResponseEntity<?> getSmallestPopCountry() {
		Country rtnCountry = Collections.min(JavaCountriesApplication.countryData.countryList, Comparator.comparing(c -> (int)c.getPopulation()));
		return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
	}

	@GetMapping(value = "/max", produces = {"application/json"})
	public ResponseEntity<?> getBiggestPopCountry() {
		Country rtnCountry = Collections.max(JavaCountriesApplication.countryData.countryList, Comparator.comparing(c -> (int)c.getPopulation()));
		return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
	}
}
