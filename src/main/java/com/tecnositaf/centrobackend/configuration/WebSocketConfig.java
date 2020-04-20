package com.tecnositaf.centrobackend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocketConfig is annotated with @Configuration to indicate that it is a
 * Spring configuration class. It is also annotated
 * with @EnableWebSocketMessageBroker. As its name
 * suggests, @EnableWebSocketMessageBroker enables WebSocket message handling,
 * backed by a message broker.
 * 
 * @author niccolo mondino
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.setApplicationDestinationPrefixes("/app/devices").enableSimpleBroker("/topic");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// end point for web socket front-end
		registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:4200").withSockJS();
	}

	
}