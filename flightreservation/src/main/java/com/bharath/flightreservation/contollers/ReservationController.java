package com.bharath.flightreservation.contollers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightreservation.dto.ReservationRequest;
import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repos.FlightRepository;
import com.bharath.flightreservation.services.ReservationService;

@Controller
public class ReservationController {
	
	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private ReservationService reservationService;  
	
	@RequestMapping(value="/showCompleteReservation" , method=RequestMethod.GET)
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap  ) {
		
		Optional<Flight> flight = flightRepository.findById(flightId);
		modelMap.addAttribute("flight" ,flight.get() );
		return "completeReservation";
	}
	
	@RequestMapping(value="/CompleteReservation" , method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request , ModelMap modelMap) {
	
		System.out.println(request.getFlightId());
		System.out.println(request);
		
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg" , "Reservation created successfully and id is " + reservation.getId() );
		
		return "reservationConfirmation";
	}
	
	
}
