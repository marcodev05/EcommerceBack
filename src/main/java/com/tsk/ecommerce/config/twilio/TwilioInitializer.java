package com.tsk.ecommerce.config.twilio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioInitializer {

	private TwilioConfiguration twilioConfiguration;
	private final Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);

	@Autowired
	public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
		super();
		this.twilioConfiguration = twilioConfiguration;
		Twilio.init(twilioConfiguration.getAccountSid(), 
					twilioConfiguration.getAuthToken());
		LOGGER.info("Twilio initialized ... with sid {} ", twilioConfiguration.getAccountSid());
	}
	
	
	
}
