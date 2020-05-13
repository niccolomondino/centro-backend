package com.tecnositaf.centrobackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.utilities.CommonsUtility;

@RestController
public class WebSocketRestController {

	private final SimpMessagingTemplate template;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WebSocketRestController(SimpMessagingTemplate template) {
		this.template = template;
	}
	
	@PostMapping("/send/alert")
	public void onReceivedAlert(@RequestBody DTOAlert dtoAlert) throws Exception {
		
		logger.info("---------- POST /send/alert ----------");
		
		if (CommonsUtility.isNull(dtoAlert)) {
			throw new FailureException(Errors.NOT_ALLOWED,HttpStatus.BAD_REQUEST);
		}
		
		logger.info("---------- New Alert detected ----------");
		
		logger.info("---------- Sending Alert to Client ----------");
		
		this.template.convertAndSend("/topic/messages", dtoAlert);
	}
	
	
}
