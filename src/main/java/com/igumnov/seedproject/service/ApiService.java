package com.igumnov.seedproject.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.igumnov.seedproject.model.City;
import com.igumnov.seedproject.model.Country;
import com.igumnov.seedproject.report.CustomJRDataSource;
import com.igumnov.seedproject.repository.CityRepository;
import com.igumnov.seedproject.repository.CountryRepository;

@Component
public class ApiService {
	final static Logger logger = Logger.getLogger(ApiService.class);

	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CountryRepository countryRepository;

	public void addCity(String cityName, String countryName, Long population) {
		City city = new City().setName(cityName).setPopulation(population);
		Country country = countryRepository.findOne(countryName);
		city.setCountry(country);
		country.addCity(city);
		cityRepository.save(city);

	}

	public void addCountry(String countryName) {
		Country country = new Country().setName(countryName);
		country = countryRepository.save(country);
	}

	public void generateReport(List<City> cities) throws JRException {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		jasperReport = JasperCompileManager
				.compileReport("jasper/report.jrxml");
		CustomJRDataSource<City> dataSource = new CustomJRDataSource<City>()
				.initBy(cities);
		jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),
				dataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"jasper/report.pdf");
	}

	@PostConstruct
	public void init() {
		logger.info("ApiService.init()");
		addCountry("USA");
		addCountry("RUSSIA");
		addCity("Los Angeles", "USA", 10000L);
		addCity("New York", "USA", 20000L);
		addCity("Washington", "USA", 30000L);
		addCity("Moscow", "RUSSIA", 90000L);
		addCity("Novosibirsk", "RUSSIA", 10000L);
	}
}
