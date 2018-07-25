package com.aerohive.nms.a3.communicator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aerohive.nms.a3.communicator.handler.MessageRedisCacheHandler;
import com.aerohive.nms.a3.communicator.service.MessageReceiveService;
import com.aerohive.nms.a3.message.A3Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageReceiveServiceImpl implements MessageReceiveService {
	
	@Autowired
	private MessageRedisCacheHandler msgCacheHandler;

	@Override
	public boolean synMessageProcess(List<A3Message> messages) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean asynMessageProcess(List<A3Message> messages) {
		msgCacheHandler.cacheMessage(messages);
		return false;
	}
}
