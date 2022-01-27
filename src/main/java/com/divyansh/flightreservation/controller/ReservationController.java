package com.divyansh.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.divyansh.flightreservation.controller.dto.ReservationRequest;
import com.divyansh.flightreservation.entity.Flight;
import com.divyansh.flightreservation.entity.Reservation;
import com.divyansh.flightreservation.repo.FlightRepo;
import com.divyansh.flightreservation.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	FlightRepo flightRepo;
	
	@Autowired
	private ReservationService reservationService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@RequestMapping("/showCompleteReservation")
	private String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {

		LOGGER.info("Inside showCompleteReservation() with flightId: " + flightId);
		
		Flight flight = flightRepo.findById(flightId).get();

		modelMap.addAttribute("flight", flight);
		
		LOGGER.info("Flight is: " + flight);

		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation", method=RequestMethod.POST)
	public String completeReservation(ReservationRequest reservationRequest, ModelMap modelMap) {
		
		LOGGER.info("Inside completeReservation() with Reservation Request: " + reservationRequest);
		
		Reservation reservation = reservationService.bookFlight(reservationRequest);
		modelMap.addAttribute("msg", "Reservation created successfully with the id: " + reservation.getId());
		
		return "reservationConfirmation";
	}
	

}
