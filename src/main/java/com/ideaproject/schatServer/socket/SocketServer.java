package com.ideaproject.schatServer.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ideaproject.schatServer.thread.ChatThread;

import jakarta.annotation.PreDestroy;

@Component
public class SocketServer {
	private static final Logger log = LoggerFactory.getLogger(SocketServer.class);
	private ServerSocket serverSocket;
	private List<ChatThread> chatThreads = Collections.synchronizedList(new ArrayList<>());
	private final ExecutorService executorService = Executors.newFixedThreadPool(3); // 스레드풀 생성

	@EventListener(ApplicationReadyEvent.class)
	@Async
	public void startServer() {
		log.debug("log test!");
		try {
			serverSocket = new ServerSocket(8887);
			log.info("Server socket started on port 8887");
			while (true) {
				Socket socket = serverSocket.accept();
				executorService.submit(() -> {
					ChatThread chatThread = null;
					try {
						chatThread = new ChatThread(socket, chatThreads);
						chatThread.start();
						log.info("Client connected: " + socket.getInetAddress());
						log.info("Client name: " + chatThread.getName());
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});
			}
		} catch (IOException e) {
			log.error("Error starting server socket : " + e);
		}
	}

	@PreDestroy
	public void stopServer() {
		try {
			// 종료 시점에 호출될 메소드
			System.out.println("Cleanup called!");
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			} //server socket closed
			for(ChatThread c : chatThreads) {
				if(c != null && !c.getSocket().isClosed()) {
					c.getSocket().close();
				}
			} //clinet socket closed
		} catch (Exception e) {
			log.error("Error End server socket : " + e); // 예외가 발생하면 로그를 기록
		}
	}
}
