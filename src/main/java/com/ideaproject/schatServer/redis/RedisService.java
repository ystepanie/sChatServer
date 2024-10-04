package com.ideaproject.schatServer.redis;

import java.util.Map;

public interface RedisService {
	public Map<String, Object> getValueToMap(String key);

	public int postValueToDto(String key, Map<String, Object> value);
}
