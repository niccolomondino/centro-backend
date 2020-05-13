package com.tecnositaf.centrobackend.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.HtmlUtils;

import com.tecnositaf.centrobackend.model.ResponseMessage;

@Controller
public class WebSocketController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final SimpMessagingTemplate template;
	
	@Autowired
	WebSocketController(SimpMessagingTemplate template) {
		this.template = template;
	}
	
	@MessageMapping("/simpleMessage")
	public void onReceivedMessage(String message) throws Exception {
		logger.info(message);
		this.template.convertAndSend("/topic/messages",
				"Message received on date:" +
				new SimpleDateFormat("HH:mm:ss").format(new Date()) +
				"-" + message);
	}

	/**
	 * IMPORTANT After handling our message, we send it to the appropriate
	 * destination defined with the @SendTo annotation. All subscribers to the
	 * “/topic/messages” destination will receive the message.
	 * 
	 * @param message
	 * @return ResponseMessage
	 * @throws Exception
	 */
	@MessageMapping("/newDevice")
	@SendTo("/topic/messages")
	public ResponseMessage createNewDevice(String message) throws Exception {
		// creating new Device
		return new ResponseMessage("This is a message from endpoint \"/devices\"",
				"The message" + HtmlUtils.htmlEscape(message) + "was received!");
	}

	@MessageMapping("/alert")
	public void onReceivedAlert(String alert) throws Exception {
		this.template.convertAndSend("/topic/alerts", 
				new SimpleDateFormat("HH:mm:ss").format(new Date()) + 
				"-" + ServletUriComponentsBuilder.fromCurrentRequest().toUriString() +
				"-" + alert + HtmlUtils.htmlEscape(alert) + "!");// add html escape char at the end of the message
	}

}
