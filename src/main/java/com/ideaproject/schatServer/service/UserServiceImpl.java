package com.ideaproject.schatServer.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideaproject.schatServer.dto.UserProfileDto;
import com.ideaproject.schatServer.exception.InvalidRequestException;
import com.ideaproject.schatServer.exception.UnexpectedException;
import com.ideaproject.schatServer.redis.RedisService;
import com.ideaproject.schatServer.entity.UserProfile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final RedisService redisService;

	@Override
	public UserProfile selectUserProfile(String userId) throws Exception {
		Map<String, Object> redisMap = redisService.getValueToMap(userId);

		ObjectMapper objectMapper = new ObjectMapper();
		UserProfile userProfile = objectMapper.convertValue(redisMap, UserProfile.class);
		if(userProfile == null) {
			throw new InvalidRequestException("해당하는 프로필 정보가 없습니다.");
		}

		return userProfile;
	}

	@Override
	public int insertUserProfile(UserProfileDto userProfileDto) throws Exception {
		String key = userProfileDto.getName();

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> value = objectMapper.convertValue(userProfileDto, Map.class);
		int result = redisService.postValueToDto(key, value);
		if(result < 1) {
			throw new UnexpectedException("레디스 데이터 저장에 실패했습니다.");
		}

		return result;
	}
}
