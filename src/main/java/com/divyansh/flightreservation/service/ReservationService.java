package com.divyansh.flightreservation.service;

import com.divyansh.flightreservation.controller.dto.ReservationRequest;
import com.divyansh.flightreservation.entity.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest reservationRequest);
}
