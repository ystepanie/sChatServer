package com.ideaproject.schatServer.dto;

import org.springframework.context.annotation.Description;

import lombok.Getter;

@Description("유저 dto")
@Getter
public class UserProfileDto {
	private String name;
	private String content;
}
