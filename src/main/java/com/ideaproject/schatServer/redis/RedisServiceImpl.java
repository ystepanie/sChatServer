package com.ideaproject.schatServer.redis;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
	private final RedisTemplate redisTemplate;

	@Override
	public Map<String, Object> getValueToMap(String key) {
		// ObjectMapper를 사용하여 객체를 Map으로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> returnMap = objectMapper.convertValue(redisTemplate.opsForValue().get(key), Map.class);

		return returnMap;
	}

	@Override
	public int postValueToDto(String key, Map<String, Object> value) {
		ValueOperations<String, Object> vps =  redisTemplate.opsForValue();
		int result = 1;
		try {
			vps.set(key, value);
		} catch (Exception e) {
			// TODO: handle exception
			result = 0;
		}

		return result;
	}
}
