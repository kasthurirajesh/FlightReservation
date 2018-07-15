package com.bharath.flightreservation.repos;

import org.springframework.data.repository.CrudRepository;

import com.bharath.flightreservation.entities.Passenger;

public interface PassengerRepository extends CrudRepository<Passenger, Long> {

}
