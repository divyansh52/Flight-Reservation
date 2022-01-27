package com.divyansh.flightreservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divyansh.flightreservation.entity.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

}
