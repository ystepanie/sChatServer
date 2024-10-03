package com.ideaproject.schatServer.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideaproject.schatServer.dto.UserDto;
import com.ideaproject.schatServer.response.Response;
import com.ideaproject.schatServer.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/schatServer/user")
@RequiredArgsConstructor
@Tag(name = "userController", description = "유저 관련 api")
public class UserController {
	private final UserService userService;

	// 프로필 조회
	@GetMapping(value = "/getUserProfile/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response selectUserProfile(@PathVariable(name = "userId") String userId) throws Exception {
		Map<String, Object> resultMap = userService.selectUserProfile(userId);

		//status check
		Optional<Object> nullableStatus = Optional.ofNullable(resultMap.get("status"));
		Optional<Object> nullableMessage = Optional.ofNullable(resultMap.get("message"));

		int status = (int) nullableStatus.orElse(400);
		String message = (String) nullableMessage.orElse("유저 정보가 없습니다.");
		String data = (String) resultMap.get("data");

		return new Response(status, message, data);
	}
}
