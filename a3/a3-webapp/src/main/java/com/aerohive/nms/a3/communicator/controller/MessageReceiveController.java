package com.aerohive.nms.a3.communicator.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.aerohive.nms.a3.communicator.handler.MessageSendHandler;
import com.aerohive.nms.a3.communicator.service.MessageReceiveService;
import com.aerohive.nms.a3.message.A3Message;
import com.aerohive.nms.a3.message.A3RequestMessage;
import com.aerohive.nms.a3.message.A3ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/a3/rest/message")
public class MessageReceiveController {
	
	private static final long SYN_REQUEST_TIMEOUT = 1000 * 60 * 5;

	@Autowired
	private MessageReceiveService msgReceiveService;
	@Autowired
	private MessageSendHandler sender;
	
	@RequestMapping(value="/hello", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> hello() {
		A3ResponseMessage msg = new A3ResponseMessage();
		msg.setErrorMsg("aaaahhhhafda");
		sender.broadcastMessageResult(msg);
		return new ResponseEntity<>(OK);
	}
	
	@RequestMapping(value="/push/syn",method = POST)
	public DeferredResult<String> synMessagepush(@RequestBody A3RequestMessage messages) {
		DeferredResult<String> result = new DeferredResult<String>(SYN_REQUEST_TIMEOUT);
		msgReceiveService.synMessageProcess(messages, result);
		return result;
	}

	@RequestMapping(value="/push/asyn",method = POST)
	public ResponseEntity<Void> asynMessagepush(@RequestBody List<A3Message> messages) {
		
		msgReceiveService.asynMessageProcess(messages);
		return new ResponseEntity<>(OK);
	}
}
