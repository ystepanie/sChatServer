package com.ideaproject.schatServer.vo;

import org.springframework.context.annotation.Description;

import lombok.Getter;
import lombok.Setter;

@Description("유저 프로필 vo")
@Getter
@Setter
public class UserProfileVo {
	private String name;
	private String content;
}
