package com.ideaproject.schatServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	@Bean
	// redisConnectionFactory - redis 서버와의 연결을 관리하는 객체
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory); // 스프링과 레디스간의 연결을 담당
		template.setKeySerializer(new StringRedisSerializer()); // 데이터 저장 시 키를 직렬하는 방식(String 형태로)
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // 값을 직렬화하는 방식(Json 형태로)
		return template;
	}
}
