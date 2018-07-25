package com.aerohive.nms.a3.communicator.service;

import java.util.List;

import com.aerohive.nms.a3.message.A3Message;

public interface MessageReceiveService {
	
	boolean synMessageProcess(List<A3Message> messages);
	
	boolean asynMessageProcess(List<A3Message> messages);
	
//	public List<String> getAllMessages(String key);
}
