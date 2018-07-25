package com.aerohive.nms.a3.communicator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aerohive.nms.a3.communicator.handler.MessageRedisCacheHandler;
import com.aerohive.nms.a3.communicator.handler.MessageSendHandler;
import com.aerohive.nms.a3.communicator.service.A3PollingService;
import com.aerohive.nms.a3.message.A3Message;
import com.aerohive.nms.a3.message.A3ResponseMessage;

@Service
public class A3PollingServiceImpl implements A3PollingService {
	
	@Autowired
	private MessageRedisCacheHandler msgCacheHandler;
	@Autowired
	private MessageSendHandler sender;

	@Override
	public List<String> getAllMessages(String key) {
		return msgCacheHandler.getAllMessages(key);
	}

	@Override
	public void handleMessage(List<A3Message> messages) {
		if (messages == null || messages.isEmpty()) {
			return;
		}
		
		messages.stream().forEach(item -> handleMessage(item));
	}
	
	
	public void handleMessage(A3Message messages) {
		if (messages instanceof A3ResponseMessage) {
			sender.broadcastMessageResult((A3ResponseMessage)messages);
		} else {
			// TODO
		}
	}

}
