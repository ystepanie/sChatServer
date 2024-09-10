package com.ideaproject.schatServer.service;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ideaproject.schatServer.dto.ServerSocketDto;
import com.ideaproject.schatServer.thread.ChatThread;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {
	@Override
	public Map<String, Object> generateSocket(ServerSocketDto serverSocketDto) throws Exception {
		// 서버 소켓 생성
		ServerSocket serverSocket = new ServerSocket(8888);

		List<ChatThread> list = Collections.synchronizedList(new ArrayList<>());

		// while(true) {
		// 	// 클라이언트가 연결되기 전까지 블로킹 상태로 있다가 연결되면 소켓 반환
		// 	Socket socket = serverSocket.accept();
		// 	ChatThread chatThread = new ChatThread(socket, list);
		// 	chatThread.start();
		// }

		Map<String ,Object> testMap = new HashMap<>();

		return testMap;
	}
}
