package com.ideaproject.schatServer.service;

import java.util.Map;

import com.ideaproject.schatServer.dto.ClientInitDto;

public interface ClientService {

	//클라이언트 소켓 전달
	public Map<String, Object> startClientConnect(ClientInitDto clientSocketDto) throws Exception;
}
