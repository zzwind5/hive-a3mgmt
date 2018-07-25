package com.aerohive.nms.a3.communicator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aerohive.nms.a3.communicator.handler.MessageRedisCacheHandler;
import com.aerohive.nms.a3.communicator.service.A3PollingService;

@Service
public class A3PollingServiceImpl implements A3PollingService {
	
	@Autowired
	private MessageRedisCacheHandler msgCacheHandler;

	@Override
	public List<String> getAllMessages(String key) {
		return msgCacheHandler.getAllMessages(key);
	}

}
