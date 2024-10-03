package com.ideaproject.schatServer.service;

import java.util.Map;

import com.ideaproject.schatServer.dto.UserDto;

public interface UserService {

	//유저 프로필 조회
	Map<String, Object> selectUserProfile(String userId) throws Exception;
}
