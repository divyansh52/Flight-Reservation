package com.divyansh.flightreservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Value("${com.divyansh.flightreservation.itinerary.email.body}")
	private String EMAIL_BODY;

	@Value("${com.divyansh.flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT;

	@Autowired
	private JavaMailSender sender;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);
	
	public void sendItinerary(String toAddress, String filePath) {
		
		LOGGER.info("Inside sendItinerary()");
		
		MimeMessage message = sender.createMimeMessage();
		
		// Since we are sending an attachment, so we are passing a boolean value(true) as a parameter
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(toAddress);
			helper.setSubject(EMAIL_SUBJECT);
			helper.setText(EMAIL_BODY);
			helper.addAttachment("Itinerary", new File(filePath));
			
			
		} catch (MessagingException e) {
			
			LOGGER.error("Exception inside sendItinerary() " + e);
			
			e.printStackTrace();
		}
		
		sender.send(message);
	}


}
