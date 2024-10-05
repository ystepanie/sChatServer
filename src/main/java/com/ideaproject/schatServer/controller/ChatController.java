package com.ideaproject.schatServer.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ideaproject.schatServer.entity.Message;

@Controller
public class ChatController {

	@MessageMapping("/chat") // 다음 경로에서 메시지를 받는다.
	@SendTo("/topic/messages") // 다음 구독자에게 메시지를 보낸다.
	public Message sendMessage(Message message) {
		return message;
	}
}
