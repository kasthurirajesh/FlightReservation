<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flight Details</title>
</head>
<body>
Airlines : ${reservation.flight.operatingAirlines}<br>
Flight No : ${reservation.flight.flightNumber} <br>
Departure City : ${reservation.flight.flightNumber}<br>
Arrival City : ${reservation.flight.arrivalCity}<br>
Date Of Departure : ${reservation.flight.dateOfDeparture}<br>
Estimated Departure: ${reservation.flight.estimatedDepartureTime}<br>

<h2>Passenger Details  </h2>

First Name : ${reservation.passenger.firstName } <br>
Last Name : ${reservation.passenger.lastName }<br>
Email  : ${reservation.passenger.email } <br>
Phone : ${reservation.passenger.phone } <br>

<form action="completeCheckIn" method ="post">
	Enter the NUmber of Bags  Want to check  In: <input type="text" name="numberOfBags">
	<input type="hidden" value="${reservation.id}" name="reservationId"/>
	<input type="submit" value="CHECK IN" />
</form>

</body>
</html>