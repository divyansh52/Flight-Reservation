package com.divyansh.flightreservation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.divyansh.flightreservation.controller.dto.ReservationRequest;
import com.divyansh.flightreservation.entity.Flight;
import com.divyansh.flightreservation.entity.Passenger;
import com.divyansh.flightreservation.entity.Reservation;
import com.divyansh.flightreservation.repo.FlightRepo;
import com.divyansh.flightreservation.repo.PassengerRepo;
import com.divyansh.flightreservation.repo.ReservationRepo;
import com.divyansh.flightreservation.util.EmailUtil;
import com.divyansh.flightreservation.util.PdfGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.divyansh.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	@Autowired
	FlightRepo flightRepo;

	@Autowired
	PassengerRepo passengerRepo;

	@Autowired
	ReservationRepo reservationRepo;

	@Autowired
	PdfGenerator pdfGenerator;

	@Autowired
	EmailUtil emailUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest reservationRequest) {

		LOGGER.info("Inside bookFlight()");
		
		// Make Payment

		Long flightId = reservationRequest.getFlightId();
		
		LOGGER.info("Fetching flight for Flight Id: " + flightId);
		
		Flight flight = flightRepo.findById(flightId).get();


		
		Passenger passenger = new Passenger();

		passenger.setFirstName(reservationRequest.getPassengerFirstName());
		passenger.setLastName(reservationRequest.getPassengerLastName());
		passenger.setEmail(reservationRequest.getPassengerEmail());
		passenger.setPhone(reservationRequest.getPassengerPhone());

		LOGGER.info("Saving the passenger: " + passenger);
		
		Passenger savedPassenger = passengerRepo.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		LOGGER.info("Saving the reservation: " + reservation);

		Reservation savedReservation = reservationRepo.save(reservation);

		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";

		LOGGER.info("Generating the itinerary");
		
		pdfGenerator.generateItinerary(savedReservation, filePath);

		LOGGER.info("Emailing the itinerary");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

}
