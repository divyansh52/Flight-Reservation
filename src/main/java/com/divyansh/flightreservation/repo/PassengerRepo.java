package com.divyansh.flightreservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divyansh.flightreservation.entity.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {

}
