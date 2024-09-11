package com.ideaproject.schatServer.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideaproject.schatServer.dto.ClientInitDto;
import com.ideaproject.schatServer.response.Response;
import com.ideaproject.schatServer.service.ClientService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/schatServer/client")
@RequiredArgsConstructor
@Tag(name = "clientController", description = "클라이언트 채팅 관련 api")
public class ClientController {
	private final ClientService clientService;

	//todo: 프로젝트 실행 시 서버 소켓 생성(서버 소켓은 while문을 통해 계속 클라이언트의 입장을 받아야 한다.)
	//ㄴ 클라이언트 접속 시 스레드 생성
	//todo: 전챗 방 들어가기
	//ㄴ 방 접속 시 클라이언트 스레드 객체 생성
	//ㄴ 메시지 입력하면 서버에서 받아서 클라이언트 스레드를 통해 뿌려줌
	//ㄴ 접속 종료 시 스레드 종료

	// 클라이언트 연결 api
	@PostMapping("/startClientConnect")
	public Response startClientConnect(@RequestBody ClientInitDto clientInitDto) throws Exception {
		Map<String, Object> resultMap = clientService.startClientConnect(clientInitDto);

		//status check
		Optional<Object> nullableStatus = Optional.ofNullable(resultMap.get("status"));
		Optional<Object> nullableMessage = Optional.ofNullable(resultMap.get("message"));

		int status = (int) nullableStatus.orElse(400);
		String message = (String) nullableMessage.orElse("status null error!!!");

		return new Response(status, message);
	}

}
