package com.aerohive.nms.a3.communicator.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aerohive.nms.a3.communicator.handler.MessageSendHandler;
import com.aerohive.nms.a3.communicator.service.A3PollingService;
import com.aerohive.nms.a3.message.A3Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/a3/rest/v1")
public class PollController {
	
	@Autowired
	private A3PollingService pollingService;

	@GetMapping(value="/poll/{sysId}", produces = APPLICATION_JSON_VALUE)
	public List<String> handlePollingRequest(@PathVariable final String sysId,
			@RequestParam(required = false) final String clustId) {
		
		String key = StringUtils.isEmpty(clustId) == false ? clustId : sysId;
		return pollingService.getAllMessages(key);
	}
	
	@PostMapping(value="/report/{sysId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> handleReportMessage(@PathVariable final String sysId,
			@RequestBody List<A3Message> messages) {
		
		pollingService.handleMessage(messages);
		return new ResponseEntity<>(OK);
	}
}
