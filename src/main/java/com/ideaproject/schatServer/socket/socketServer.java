package com.ideaproject.schatServer.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ideaproject.schatServer.thread.ChatThread;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class socketServer {
	private ServerSocket serverSocket;
	private List<ChatThread> chatThreads = Collections.synchronizedList(new ArrayList<>());
	private volatile boolean running = true;

	@PostConstruct
	public void startServer() throws IOException {
		serverSocket = new ServerSocket(8887);

		while (running) {
			try {
				Socket socket = serverSocket.accept();
				if (!running) {
					break;
				}
				ChatThread chatThread = new ChatThread(socket, chatThreads);
				chatThread.start();
			} catch (IOException e) {
				if (running) {
					throw e; // rethrow exception if server is still running
				}
			}
		}
	}

	@PreDestroy
	public void stopServer() throws IOException {
		running = false;
		if (serverSocket != null && !serverSocket.isClosed()) {
			serverSocket.close();
		}

	}
}
