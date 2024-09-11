package com.ideaproject.schatServer.dto;

import org.springframework.context.annotation.Description;

import lombok.Getter;
import lombok.Setter;

@Description("클라이언트 소켓 dto")
@Getter
public class ClientInitDto {
	private String name;
}
