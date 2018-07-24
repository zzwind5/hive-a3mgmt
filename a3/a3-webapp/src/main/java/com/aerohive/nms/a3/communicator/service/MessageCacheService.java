package com.aerohive.nms.a3.communicator.service;

import java.util.List;

import com.aerohive.nms.a3.message.A3Message;

public interface MessageCacheService {

	public boolean cacheMessage(List<A3Message> messages);
	
	public boolean cacheMessage(A3Message message);
	
	public List<String> getAllMessages(String key);
}
