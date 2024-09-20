package com.ideaproject.schatServer.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatHandler extends TextWebSocketHandler {
	private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
	private final static Logger log = LoggerFactory.getLogger(ChatHandler.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// JSON 메시지 파싱
		Map<String, Object> payload = objectMapper.readValue(message.getPayload(), Map.class);

		for (WebSocketSession webSocketSession : sessions) {
			if ("nickname".equals(payload.get("type"))) {
				// 닉네임 메시지 처리
				String nickname = (String)payload.get("content");
				session.getAttributes().put("nickname", nickname);
				System.out.println("User connected with nickname: " + nickname);
				webSocketSession.sendMessage(new TextMessage("Welcome " + nickname));
			} else {
				// 일반 채팅 메시지 처리
				String chatMessage = (String) payload.get("content");
				String nickname = (String) session.getAttributes().get("nickname");
				System.out.println("Message from user: " + chatMessage);
				 webSocketSession.sendMessage(new TextMessage(nickname + " : " + chatMessage));
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("afterConnectionClosed : {} remove", session.getId());
		sessions.remove(session);
		sendMessageToAll("user left");
	}

	// 전체 세션에 메시지 전송
	private void sendMessageToAll(String message) {
		for (WebSocketSession session : sessions) {
			try {
				session.sendMessage(new TextMessage(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
