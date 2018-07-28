package com.aerohive.nms.a3.communicator.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.aerohive.nms.a3.message.A3Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageRedisCacheHandler {

	private static final String MESSAGE_CACHED_KEY = "A3.messages.list.{key}";
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	public boolean cacheMessage(List<A3Message> messages) {
		messages.stream().forEach(item -> cacheMessage(item));
		return true;
	}

	public boolean cacheMessage(A3Message message) {
		try {
			redisTemplate.opsForList().rightPush(getCacheKey(message), objectMapper.writeValueAsString(message));
		} catch (JsonProcessingException e) {
			return false;
		}
		return true;
	}

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
				message.getClusterId() : message.getSysId();
				
		return getCacheKey(key);
	}
	
	private String getCacheKey(String key) {
		return MESSAGE_CACHED_KEY.replace("{key}", key);
	}
}
