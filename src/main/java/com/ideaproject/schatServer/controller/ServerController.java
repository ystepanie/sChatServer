package com.ideaproject.schatServer.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideaproject.schatServer.response.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/schatServer")
@RequiredArgsConstructor
public class ServerController {
	//todo: 프로젝트 실행 시 서버 소켓 생성(서버 소켓은 while문을 통해 계속 클라이언트의 입장을 받아야 한다.)
	//ㄴ 클라이언트 접속 시 스레드 생성
	//todo: 웨이팅 방 들어가기(클라이언트 프로젝트)
	//ㄴ 최초 접속 시 서버 켜져 있나 확인 후 없으면 오류 페이지
	//ㄴ 방 접속 시 클라이언트 스레드 객체 생성
	//ㄴ 메시지 입력하면 서버에서 받아서 클라이언트 스레드를 통해 뿌려줌
	//ㄴ 접속 종료 시 스레드 종료

	// 소켓 생성 api
	@PostMapping("/generateSocket")
	public Response postLogin(@RequestBody ServerSocketDto serverSocketDto) throws Exception {
		Map<String, Object> resultMap = serverService.generateSocket(serverSocketDto);

		//status check
		Optional<Object> nullableStatus = Optional.ofNullable(resultMap.get("status"));
		Optional<Object> nullableMessage = Optional.ofNullable(resultMap.get("message"));

		int status = (int)nullableStatus.orElse(200);
		String message = (String)nullableMessage.orElse("status null error!!!");

		return new Response(status, message);
	}

}
