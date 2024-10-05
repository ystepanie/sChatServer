package com.ideaproject.schatServer.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideaproject.schatServer.dto.UserProfileDto;
import com.ideaproject.schatServer.response.Response;
import com.ideaproject.schatServer.service.UserService;
import com.ideaproject.schatServer.vo.UserProfileVo;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/schatServer/user")
@RequiredArgsConstructor
@Tag(name = "userController", description = "유저 관련 api")
public class UserController {
	private final UserService userService;

	// 프로필 조회
	@GetMapping(value = "/getUserProfile/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response selectUserProfile(@NonNull  @PathVariable(name = "userId") String userId) throws Exception {
		UserProfileVo userProfileVo = userService.selectUserProfile(userId);
		//TODO : vo 객체 response 객체로 변환하기

		return new Response();
	}

	// 프로필 저장
	@PostMapping(value = "/postUserProfile/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response insertUserProfile(@NonNull  @PathVariable(name = "userId") String userId,
									  @Valid @RequestBody UserProfileDto userProfileDto) throws Exception {
		// dto setting
		userProfileDto.setName(userId);

		int result = userService.insertUserProfile(userProfileDto);
		//TODO : 결과값 response 객체로 변환하기

		return new Response();
	}


}
