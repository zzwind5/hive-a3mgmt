package com.aerohive.nms.a3.communicator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import com.aerohive.nms.a3.communicator.service.MessageCacheService;
import com.aerohive.nms.a3.message.A3Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageCacheServiceImpl implements MessageCacheService {
	
	@Autowired
	private StringRedisTemplate template;

	@Override
	public boolean cacheMessage(List<A3Message> messages) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cacheMessage(A3Message message) {
		String key = getCacheKey(message);
		return false;
	}

	@Override
	public List<A3Message> getMessages(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getCacheKey(A3Message message) {
		return StringUtils.isEmpty(message.getClusterId()) ? message.getAgentId() : message.getClusterId();
	}

}
