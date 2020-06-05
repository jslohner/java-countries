package com.lambdaschool.javacountries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaCountriesApplication {
	public static CountryList countryData;

	public static void main(String[] args) {
		countryData = new CountryList();
		SpringApplication.run(JavaCountriesApplication.class, args);
	}

}
