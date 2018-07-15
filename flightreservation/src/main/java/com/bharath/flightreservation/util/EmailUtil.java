package com.bharath.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender sender;
	
	public void sendItinerary(String toAddress, String filePath ) {

		MimeMessage message=sender.createMimeMessage();
		/*
		 * true means you are attching the attachments 
		 * if u don't attach attachments set to flase 
		 */
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper( message , true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject("Itinerary for your flight");
			messageHelper.setText("Please find your Itinerary attached.");
			messageHelper.addAttachment("Itinearary", new File(filePath) );
			
			sender.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
