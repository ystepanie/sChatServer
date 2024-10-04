package com.ideaproject.schatServer.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ideaproject.schatServer.response.Response;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice()
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private final static int failCode = -1;

	@ExceptionHandler(value = { InvalidRequestException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	private Object handleInvalidRequestException(InvalidRequestException e, HttpServletRequest request) throws Exception {
		log.info("@@@ ControllerStatusAdvice - InvalidRequestException" + (! "".equals(e.getMessage()) ? " - msg->" + e.getMessage() : ""));
		return new Response(failCode, e.getMessage());
	}

	@ExceptionHandler(value = { UnexpectedException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	private Object handleUnexpectedException(UnexpectedException e, HttpServletRequest request) throws Exception {
		log.info("@@@ ControllerStatusAdvice - UnexpectedException" + (! "".equals(e.getMessage()) ? " - msg->" + e.getMessage() : ""));
		return new Response(failCode, e.getMessage());
	}
}
