package com.ideaproject.schatServer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// 메시지 브로커 활성(topic으로 시작하는 경로를 통해 메시지를 구독하면 발신자의 메시지가 구독자들에게 전달되는 방식
		config.enableSimpleBroker("/topic");
		// app으로 시작하는 메시지를 보내면 애플리케이션에서 처리(websockethandler 처리 동일)
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 웹소켓 통신 연결
		registry.addEndpoint("/ws").withSockJS();
	}
}
