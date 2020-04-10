package com.tecnositaf.centrobackend.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
		/**
		 * IMPORTANT After handling our message, we send it to the appropriate
		 * destination defined with the @SendTo annotation. All subscribers to the
		 * “/topic/messages” destination will receive the message.
		 * 
		 * @param message
		 * @return
		 * @throws Exception
		 */
		@MessageMapping("/devices")
		@SendTo("/topic/messages")
		public ResponseMessage simpleMessage(String message) throws Exception {
			storeMessage(message);
			return new ResponseMessage(
					"This is a message from endpoint \"/devices\"" ,
					"The message" + HtmlUtils.htmlEscape(message) +  "was received!"); 
		}
		
		
		
		
		
		
		
		
		
		
		
		
		@Autowired
		WebSocketController(SimpMessagingTemplate template) {
			this.template = template;
		}
		
		
		@MessageMapping("/send/message")
		public void onReceivedMessage(String message) throws Exception {
			this.template.convertAndSend("/chat", new SimpleDateFormat("HH:mm:ss").format(new Date()) + "-" + message);
		}
		
		

		
		
	public void storeMessage(String message) {
		
	}
	
	/*
	@MessageMapping("/device")
	@SendTo("/topic/messages")
	public CustomMessage simpleMessage(String message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new CustomMessage(
				"This is a message from" + ServletUriComponentsBuilder.fromCurrentRequest().toUriString() + "",
				"Message: " + HtmlUtils.htmlEscape(message) + "!"); // add html escape char at the end of the message
	}*/
	
	
	
	private final SimpMessagingTemplate template;

	
	
}
