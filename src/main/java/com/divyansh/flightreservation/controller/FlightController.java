package com.divyansh.flightreservation.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.divyansh.flightreservation.entity.Flight;
import com.divyansh.flightreservation.repo.FlightRepo;

@Controller
public class FlightController {
	
	@Autowired
	private FlightRepo flightRepo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);
	
	@RequestMapping(value="/findFlights")
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("departureDate") @DateTimeFormat(pattern="MM-dd-yyyy") Date departureDate, ModelMap modelMap) {
		
		LOGGER.info("Inside findFlights(), Flight From: " + from + " To: " + to + " Date of departure: " + departureDate);
		
		List<Flight> flights =  flightRepo.findFlights(from, to, departureDate);
		
		modelMap.addAttribute("flights", flights);
		
		LOGGER.info("Flights found are: " + flights);
		
		return "displayFlights";
		
	}
	
	@RequestMapping("/admin/showAddFlight")
	public String showAddFlight() {
		return "addFlight";
	}

}
