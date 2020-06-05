package com.lambdaschool.javacountries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/names")
public class NamesController {

	@GetMapping(value = "/all", produces = {"application/json"})
	public ResponseEntity<?> getAllCountries() {
	JavaCountriesApplication.countryData.countryList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
	return new ResponseEntity<>(JavaCountriesApplication.countryData.countryList, HttpStatus.OK);
	}

	@GetMapping(value = "/start/{nameLetter}", produces = {"application/json"})
	public ResponseEntity<?> getCountriesByStartingLetter(@PathVariable char nameLetter) {
	List<Country> filteredList = JavaCountriesApplication.countryData.findCountries(c -> (Character.toLowerCase(c.getName().charAt(0)) == Character.toLowerCase(nameLetter)));
	filteredList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
	return new ResponseEntity<>(filteredList, HttpStatus.OK);
	}

	@GetMapping(value = "/size/{nameLength}", produces = {"application/json"})
	public ResponseEntity<?> getCountriesByNameLength(@PathVariable long nameLength) {
	List<Country> filteredList = JavaCountriesApplication.countryData.findCountries(c1 -> c1.getName().length() >= nameLength);
	filteredList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
	return new ResponseEntity<>(filteredList, HttpStatus.OK);
	}
}
