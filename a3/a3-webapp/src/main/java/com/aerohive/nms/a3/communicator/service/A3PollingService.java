package com.aerohive.nms.a3.communicator.service;

import java.util.List;

import com.aerohive.nms.a3.message.A3Message;

public interface A3PollingService {

	List<String> getAllMessages(String key);
	
	void handleMessage(List<A3Message> messages);
}
