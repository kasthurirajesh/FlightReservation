package com.bharath.flightreservation.contollers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {

	@Autowired
	private FlightRepository flightRepository ;
	
	@RequestMapping(value="/findFlights" , method=RequestMethod.POST)
	public String findFlights(@RequestParam("from") String from , @RequestParam("to") String to, 
			@RequestParam("departureDate")@DateTimeFormat(pattern="MM-dd-yyyy") Date departure , ModelMap modelMap) {
	
		System.out.println("from " + from);
		System.out.println("to" + to);
		System.out.println("date" + departure);
		List<Flight> data = flightRepository.findFlight(from , to , departure);
		System.out.println(data +" data ");
		
		modelMap.addAttribute("flights" , data);
		
		
		return "displayFlight";
	}
}
