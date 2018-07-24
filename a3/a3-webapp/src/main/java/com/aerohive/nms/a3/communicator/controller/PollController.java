package com.aerohive.nms.a3.communicator.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.aerohive.nms.a3.message.A3Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/a3/rest/v1/poll/{sn}")
public class PollController {
	

	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
	public DeferredResult<List<A3Message>> handlePollingRequest(@PathVariable final String sn) {
		log.trace("<{}> Received query for work items", sn);
		
		return null;
	}
}
