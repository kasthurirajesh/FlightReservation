package com.bharath.flightreservation.contollers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.flightreservation.dto.ReservationUpdateRequest;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repos.ReservationRepository;

@RestController
public class ReservationRESTController {

	/*
	 * @PathVariable used for accessing the values from the URI template.
	 */

	@Autowired
	ReservationRepository reservationRepository;

	@RequestMapping(value = "/reservations/{id}", method = RequestMethod.GET)
	public Reservation findReservation(@PathVariable("id") Long id) {
		System.out.println("id" + id);
		Optional<Reservation> option = reservationRepository.findById(id);
		Reservation reservation = option.get();
		return reservation;
	}

	/*@RequestBody ReservationUpdateRequest request
	 * Above line meaning is that construct the object which is coming from the response body
	 */
	
	@RequestMapping(value = "/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		Optional<Reservation> option = reservationRepository.findById(request.getId());

		Reservation reservation = option.get();
		reservation.setCheckedIn(request.getCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		Reservation reservationSaved=reservationRepository.save(reservation);
		return reservationSaved;

	}

}
