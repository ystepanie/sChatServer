package com.ideaproject.schatServer.service;

import com.ideaproject.schatServer.dto.UserProfileDto;
import com.ideaproject.schatServer.vo.UserProfileVo;

public interface UserService {

	//유저 프로필 조회
	UserProfileVo selectUserProfile(String userId) throws Exception;

	//유저 프로필 저장
	int insertUserProfile(UserProfileDto userProfileDto) throws Exception;
}
