package com.aerohive.nms.a3.communicator.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aerohive.nms.a3.communicator.service.MessageCacheService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/a3/rest/v1/poll/{sysId}")
public class PollController {
	
	@Autowired
	private MessageCacheService cacheService;
	

	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
	public List<String> handlePollingRequest(@PathVariable final String sysId,
			@RequestParam final String clustId) {
		
		String key = StringUtils.isEmpty(clustId) == false ? clustId : sysId;
		return cacheService.getAllMessages(key);
	}
}
