package com.aerohive.nms.a3.communicator.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

@Component
public class SynRequestCache {

	private Map<String, DeferredResult<String>> cacheMap = new ConcurrentHashMap<>();
	
	public void cache(String key, DeferredResult<String> value) {
		cacheMap.put(key, value);
	}
	
	public void notify(String key, String result) {
		if (key == null) {
			return;
		}
		
		DeferredResult<String> cacheObj = cacheMap.remove(key);
		if (cacheObj == null) {
			return;
		} else {
			cacheObj.setResult(result);
		}
	}
}
