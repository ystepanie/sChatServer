package com.ideaproject.schatServer.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;

@Getter
public class ChatThread extends Thread {
	private static final Logger log = LoggerFactory.getLogger(ChatThread.class);
	private String userName;
	private BufferedReader br;
	private PrintWriter pw;
	private Socket socket;
	List<ChatThread> list;

	public ChatThread(Socket socket, List<ChatThread> list) throws IOException {
		this.socket = socket;
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		this.br = br;
		this.pw = pw;
		this.userName = br.readLine();
		this.list = list;
		this.list.add(this);
	}

	public void sendMessage(String msg) {
		pw.println(msg);
		pw.flush();
	}

	private void broadcast(String msg, boolean includeMe){
		List<ChatThread> chatThreads = new ArrayList<>();
		for (int i=0;i<this.list.size();i++) {
			chatThreads.add(list.get(i));
		}

		try {
			for(int i=0;i<chatThreads.size();i++){
				ChatThread ct = chatThreads.get(i);
				if(ct == this && !includeMe){
					continue;
				}
				ct.sendMessage(msg);
			}
		} catch(Exception e) {
			log.error("Error broadcast : " + e);
		}
	}

	@Override
	public void run() {
		try{
			broadcast(userName + "님이 연결되었습니다.", false);
			String line = null;
			while((line = br.readLine()) != null){
				if("/quit".equals(line)){
					break;
				}
				broadcast(userName + " : " + line,true);
			}
		} catch(Exception e) {
			// ChatThread가 연결이 끊어졌다는 것.
			log.error("client connect stop : " + e);
		} finally {
			broadcast(userName + "님이 연결이 끊어졌습니다.", false);
			this.list.remove(this);

			try {
				br.close();
			} catch(Exception ex) {

			}

			try{
				pw.close();
			}catch(Exception ex){
			}

			try{
				socket.close();
			}catch(Exception ex){
			}
		}
	}
}
