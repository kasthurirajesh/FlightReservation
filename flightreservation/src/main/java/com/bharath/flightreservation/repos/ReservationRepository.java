package com.bharath.flightreservation.repos;

import org.springframework.data.repository.CrudRepository;

import com.bharath.flightreservation.entities.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
