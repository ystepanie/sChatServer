package com.ideaproject.schatServer.service;

import java.util.Map;

public interface ServerService {
	//소켓 생성
	public Map<String, Object> generateSocket(ServerSocketDto serverSocketDto) throws Exception;
}
