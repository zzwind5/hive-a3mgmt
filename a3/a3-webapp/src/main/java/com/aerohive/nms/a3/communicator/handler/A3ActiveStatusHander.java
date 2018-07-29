package com.aerohive.nms.a3.communicator.handler;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class A3ActiveStatusHander {
	
	public static final String A3_LAST_ACTIVE_TIME_KEY = "A3.last.active_time";
	private static final String KEY = "A3.lock.disconnection";
	private static final String VALUE = "Anything";
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Value("${A3.connection.timeout:60}")
	private long timeoutInteval;
	
	public void updateLastActiveTime(String systemId) {
		redisTemplate.opsForHash().put(A3_LAST_ACTIVE_TIME_KEY, systemId, String.valueOf(System.currentTimeMillis()));
	}

	public void disconnectHandle() {
		boolean hasPermission = redisTemplate.opsForValue().setIfAbsent(KEY, VALUE);
		if (hasPermission == false) {
			return;
		}
		
		log.info("A3 active status scan process running.");
		redisTemplate.expire(KEY, timeoutInteval, TimeUnit.SECONDS);
		
		long currentTime = System.currentTimeMillis();
		Map<Object, Object> activeMap = redisTemplate.opsForHash().entries(A3_LAST_ACTIVE_TIME_KEY);
		activeMap.entrySet().stream()
			.filter(entry -> {
				long cacheTime = Long.valueOf(String.valueOf(entry.getValue()));
				return cacheTime > 0 && currentTime - cacheTime > timeoutInteval * 1000;
			})
			.forEach(entry -> {
				redisTemplate.opsForHash().put(A3_LAST_ACTIVE_TIME_KEY, entry.getKey(), "-1");
				sendDisconnectMsg(String.valueOf(entry.getKey()));
			});
		
		
	}
	
	private void sendDisconnectMsg(String systemId) {
		log.info("Send A3 disconnection message to inventory system with system ID {}", systemId);
	}
}
