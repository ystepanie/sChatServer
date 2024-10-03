package com.ideaproject.schatServer.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ideaproject.schatServer.dto.UserDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Override
	public Map<String, Object> selectUserProfile(String userId) throws Exception {


		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("status", 200);
		returnMap.put("message", "connect success");
		returnMap.put("data", "null");
		return returnMap;
	}
}
