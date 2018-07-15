package com.bharath.flightcheckin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightcheckin.integration.ReservationRestClientImpl;
import com.bharath.flightcheckin.integration.dto.Reservation;
import com.bharath.flightcheckin.integration.dto.ReservationUpdateRequest;

@Controller
public class CheckInController {

	@Autowired
	ReservationRestClientImpl restClient; 
	
	@RequestMapping("/showStartCheckIn")
	public String showStartCheckin() {
		return "startCheckIn"; 
	}
	
	@RequestMapping("/startCheckIn")
	public String startCheckIn(@RequestParam("reservationId") Long reservationid, ModelMap modelMap ) {
		Reservation reservation = restClient.findReservation(reservationid);
		modelMap.addAttribute("reservation", reservation);
		return "displayReservationDetails";
	}
	
	@RequestMapping( value="/completeCheckIn" , method = RequestMethod. POST )
	public String completeCheckIn(@RequestParam("reservationId") Long reservationId, 
				@RequestParam("numberOfBags") int numberOfbags) {
		ReservationUpdateRequest request= new ReservationUpdateRequest();  	
		request.setId(reservationId);
		request.setNumberOfBags(numberOfbags);
		request.setCheckedIn(true);
		
		restClient.updateReservation(request);
		return "checkInConfirmation";
		
	}
	
}
