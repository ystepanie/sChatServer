package com.ideaproject.schatServer.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.context.annotation.Description;

import lombok.Getter;
import lombok.Setter;

@Description("유저 dto")
@Getter
@Setter
public class UserProfileDto {
	@NotBlank(message = "유저명이 없습니다.")
	private String name;

	@NotBlank(message = "프로필이 없습니다.")
	private String content;
}
