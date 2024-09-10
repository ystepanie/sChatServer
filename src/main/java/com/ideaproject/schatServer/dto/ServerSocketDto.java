package com.ideaproject.schatServer.dto;

import org.springframework.context.annotation.Description;

import lombok.Getter;
import lombok.Setter;

@Description("서버 소켓 dto")
@Getter
@Setter
public class ServerSocketDto {
	private String name;
}
