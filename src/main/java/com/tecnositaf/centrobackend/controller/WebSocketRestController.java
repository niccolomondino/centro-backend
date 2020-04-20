package com.tecnositaf.centrobackend.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.HtmlUtils;

import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.utilities.CommonsUtility;
import com.tecnositaf.centrobackend.utilities.StringUtility;

//XXX CLASS TOFIX 
@RestController
public class WebSocketRestController {

	private final SimpMessagingTemplate template;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WebSocketRestController(SimpMessagingTemplate template) {
		this.template = template;
	}
	
	@PostMapping("/send/alert")
	public void onReceivedAlert(@RequestBody String alert) throws Exception {
		
		logger.info("---------- POST /send/alert ----------");
		
		if (!StringUtility.isEmptyString(alert) || CommonsUtility.isNull(alert)) {
			throw new FailureException(Errors.NOT_ALLOWED,HttpStatus.BAD_REQUEST);
		}
		
		logger.info("---------- New Alert detected ----------");
		
		logger.info("---------- Sending Alert to Client ----------");
		
		this.template.convertAndSend("/topic/alerts", 
				new SimpleDateFormat("HH:mm:ss").format(new Date()) + 
				"-" + ServletUriComponentsBuilder.fromCurrentRequest().toUriString() +
				"-" + alert + HtmlUtils.htmlEscape(alert) + "!");// add html escape char at the end of the message
	}
	
	
}
