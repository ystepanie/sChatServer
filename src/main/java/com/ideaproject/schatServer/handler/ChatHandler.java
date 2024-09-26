package com.ideaproject.schatServer.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
	private final List<WebSocketSession> sessions = Collections.synchronizedList(new ArrayList<>());
	private final static Logger log = LoggerFactory.getLogger(ChatHandler.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}

	private void broadcastParticipantList() throws Exception {
		// 현재 세션 목록을 통해 참여자 목록 전송
		String participants = getParticipantList();
		for (WebSocketSession session : sessions) {
			session.sendMessage(new TextMessage(participants));
		}
	}

	private String getParticipantList() {
		// 세션에서 닉네임을 추출해 참여자 목록을 문자열로 만듦
		StringBuilder participants = new StringBuilder("Participants: ");
		for (WebSocketSession session : sessions) {
			String nickname = (String) session.getAttributes().get("nickname");
			participants.append(nickname).append(", ");
		}
		return participants.toString();
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// JSON 메시지 파싱
		Map<String, Object> payload = objectMapper.readValue(message.getPayload(), Map.class);

		for (WebSocketSession webSocketSession : sessions) {
			String content = (String)payload.get("content");
			if ("open".equals(payload.get("type"))) {
				// 닉네임 메시지 처리
				log.info("User connected with nickname: " + content);
				session.getAttributes().put("nickname", content);
				webSocketSession.sendMessage(new TextMessage("Welcome " + content));
				// 참여자 목록 업데이트
				broadcastParticipantList();
			} else {
				// 일반 채팅 메시지 처리
				log.info("Message from user: " + content);
				String nickname = (String) session.getAttributes().get("nickname");
				webSocketSession.sendMessage(new TextMessage(nickname + " : " + content));
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("afterConnectionClosed : {} remove", session.getId());
		sessions.remove(session); // 세션 목록에서 제거 (스레드 안전)

		String nickname = (String) session.getAttributes().get("nickname");

		for (WebSocketSession s : sessions) {
			try {
				s.sendMessage(new TextMessage("Good bye, " + nickname));
			} catch (IOException e) {
				log.error("IOException :", e);
			}
		}

		// 참여자 목록 업데이트
		broadcastParticipantList();
	}

}
