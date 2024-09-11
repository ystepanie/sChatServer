package com.ideaproject.schatServer.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ideaproject.schatServer.dto.ClientInitDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
	@Override
	public Map<String, Object> startClientConnect(ClientInitDto clientInitDto) throws Exception {
		String userName = clientInitDto.getName();

		Socket socket = new Socket("127.0.0.1", 8887);
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

		// 닉네임 전송
		pw.println(userName);
		pw.flush();

		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("status", 200);
		returnMap.put("message", "connect success");
		return returnMap;
	}
}
