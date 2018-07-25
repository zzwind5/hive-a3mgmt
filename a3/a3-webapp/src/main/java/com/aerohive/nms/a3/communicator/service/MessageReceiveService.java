package com.aerohive.nms.a3.communicator.service;

import java.util.List;

import org.springframework.web.context.request.async.DeferredResult;

import com.aerohive.nms.a3.message.A3Message;
import com.aerohive.nms.a3.message.A3RequestMessage;

public interface MessageReceiveService {
	
	boolean synMessageProcess(A3RequestMessage messages, DeferredResult<String> result);
	
	boolean asynMessageProcess(List<A3Message> messages);
}
