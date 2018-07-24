package com.aerohive.nms.a3.communicator.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aerohive.nms.a3.communicator.service.MessageCacheService;
import com.aerohive.nms.a3.message.A3Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageCacheServiceImpl implements MessageCacheService {
	
	private static final String MESSAGE_CACHED_KEY = "A3.messages.list.{key}";
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public boolean cacheMessage(List<A3Message> messages) {
		messages.stream().forEach(item -> cacheMessage(item));
		return true;
	}

	@Override
	public boolean cacheMessage(A3Message message) {
		try {
			redisTemplate.opsForList().rightPush(getCacheKey(message), objectMapper.writeValueAsString(message));
		} catch (JsonProcessingException e) {
			return false;
		}
		return true;
	}

	@Override
	public List<String> getAllMessages(String key) {
		String redKey = getCacheKey(key);
		List<String> resultList = new ArrayList<>();
		
		for(;;) {
			String messageStr = redisTemplate.opsForList().leftPop(redKey);
			if ( StringUtils.isEmpty(messageStr) ) {
				break;
			}
			resultList.add(messageStr);
		}
		
		return resultList;
	}
	
	private String getCacheKey(A3Message message) {
		String key = StringUtils.isEmpty(message.getClusterId()) == false ? 
				message.getClusterId() : message.getAgentId();
				
		return getCacheKey(key);
	}
	
	private String getCacheKey(String key) {
		return MESSAGE_CACHED_KEY.replace("{key}", key);
	}

}
