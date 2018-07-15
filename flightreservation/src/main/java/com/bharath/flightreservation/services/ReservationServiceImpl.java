package com.bharath.flightreservation.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharath.flightreservation.dto.ReservationRequest;
import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.entities.Passenger;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repos.FlightRepository;
import com.bharath.flightreservation.repos.PassengerRepository;
import com.bharath.flightreservation.repos.ReservationRepository;
import com.bharath.flightreservation.util.EmailUtil;
import com.bharath.flightreservation.util.PDFGenerator;


@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private FlightRepository  flightRepository;
	
	@Autowired
	private PassengerRepository passengerRepository; 
	
	@Autowired
	private ReservationRepository reservationRepository; 
	
	@Autowired
	private PDFGenerator pdfGenerator;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		
		
		Optional<Flight> optional=flightRepository.findById(request.getFlightId());
		Flight flight=optional.get();
		
		Passenger passenger =  new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(passenger.getPhone());
		passenger.setEmail(request.getPassengerEmail());
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		
		Reservation Reservation = new Reservation(); 
		Reservation.setCheckedIn(false);
		/* 
		 * it is a foreign key so saved passenger will keep its respective id
		 */
		Reservation.setPassenger(savedPassenger);
		//same apply for the same
		Reservation.setFlight(flight);
		Reservation saved =reservationRepository.save(Reservation);
		
		String filePath="/home/rajesh/Documents/udemy/flightreservation/src/main/java/com/bharath/flightreservation/services/"+saved.getId()+".pdf";
		
		pdfGenerator.generateItinerary(saved, filePath);
		
		emailUtil.sendItinerary(passenger.getEmail() , filePath);
		
		
		return saved;
	}

}
