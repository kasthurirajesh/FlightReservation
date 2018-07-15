package com.bharath.flightreservation.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bharath.flightreservation.entities.Flight;

public interface FlightRepository extends CrudRepository<Flight, Long> {

	/*
	 * here query is key value pair 
	 * key is class variable name 
	 * value is @param that is passing from contoller 
	 */
	
	@Query("from Flight where departureCity=:dCity and arrivalCity=:aCity and dateOfDeparture=:dateDeparture" )
	List<Flight> findFlight(@Param("dCity") String from,@Param("aCity") String to, @Param("dateDeparture") Date departure);

}
