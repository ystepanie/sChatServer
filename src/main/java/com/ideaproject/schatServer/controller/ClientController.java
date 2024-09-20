package com.ideaproject.schatServer.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;
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

	// 클라이언트 연결 api
	@PostMapping(value = "/startClientConnect", produces = MediaType.APPLICATION_JSON_VALUE)
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
