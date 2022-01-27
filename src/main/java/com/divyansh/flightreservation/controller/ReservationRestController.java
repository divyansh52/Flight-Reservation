package com.divyansh.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divyansh.flightreservation.controller.dto.ReservationUpdateRequest;
import com.divyansh.flightreservation.entity.Reservation;
import com.divyansh.flightreservation.repo.ReservationRepo;

@RestController

// Use @CrossOrigin annotation if we our our back end service to communicate with other front end service like React, Angular etc
@CrossOrigin
public class ReservationRestController {
	
	@Autowired
	ReservationRepo reservationRepo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		
		LOGGER.info("Inside findReservation() for id: " + id);
		
		return reservationRepo.findById(id).get();
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		LOGGER.info("Inside updateReservation() with ReservationUpdateRequest: " + request);
		
		Reservation reservation = reservationRepo.findById(request.getId()).get();
		
		reservation.setCheckedIn(request.getCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		
		LOGGER.info("Saving Reservation");
		
		return reservationRepo.save(reservation);
	}
}
