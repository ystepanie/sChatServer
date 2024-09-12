package com.ideaproject.schatServer.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
	// 응답 객체
	// 상태값, 메시지, 전달 데이터
	private int status;
	private String messeage;
	private String data;

	public Response() {}

	public Response(int status, String messeage, String data) {
		this.status = status;
		this.messeage = messeage;
		this.data = data;
	}

	public Response(int status, String messeage) {
		this.status = status;
		this.messeage = messeage;
	}
}
