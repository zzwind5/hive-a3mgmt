package com.aerohive.nms.a3.communicator.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aerohive.nms.a3.communicator.service.MessageCacheService;
import com.aerohive.nms.a3.message.A3Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/a3/rest/message")
public class MessageController {
	
	@Autowired
	private MessageCacheService cacheService;
	
	@RequestMapping(value="/hello", method = GET, produces = APPLICATION_JSON_VALUE)
	public String hello() {
		return "Hello";
	}

	@RequestMapping(value="/push",method = POST)
	public String pushMessage(@RequestBody List<A3Message> messages) {
		cacheService.cacheMessage(messages);
		return "successfully";
	}
}
