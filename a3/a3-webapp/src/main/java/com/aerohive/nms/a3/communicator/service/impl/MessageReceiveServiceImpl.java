package com.aerohive.nms.a3.communicator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.aerohive.nms.a3.communicator.common.SynRequestCache;
import com.aerohive.nms.a3.communicator.handler.MessageRedisCacheHandler;
import com.aerohive.nms.a3.communicator.service.MessageReceiveService;
import com.aerohive.nms.a3.message.A3Message;
import com.aerohive.nms.a3.message.A3RequestMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageReceiveServiceImpl implements MessageReceiveService {
	
	@Autowired
	private MessageRedisCacheHandler msgRedisCacheHandler;
	@Autowired
	private SynRequestCache requestCache;

	@Override
	public boolean synMessageProcess(A3RequestMessage message, DeferredResult<String> result) {
		requestCache.cache(message.getSequenceId(), result);
		msgRedisCacheHandler.cacheMessage(message);
		return true;
	}

	@Override
	public boolean asynMessageProcess(List<A3Message> messages) {
		msgRedisCacheHandler.cacheMessage(messages);
		return true;
	}
}
