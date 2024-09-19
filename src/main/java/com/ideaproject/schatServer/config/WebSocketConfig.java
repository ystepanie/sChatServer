package com.ideaproject.schatServer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.ideaproject.schatServer.handler.ChatHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// 해당 url을 통해 요청이 들어오면 웹소켓 통신 진행
		registry.addHandler(new ChatHandler(), "/chat").setAllowedOrigins("*");
	}
}
