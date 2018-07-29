package com.aerohive.nms.a3.communicator.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aerohive.nms.a3.communicator.handler.A3ActiveStatusHander;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConnectionStatusInterceptor extends HandlerInterceptorAdapter {
	
	private static final Pattern PATH_PATTERN = Pattern.compile("^/rest/v1/(?:poll|report)/([a-zA-Z0-9-]+)$");
	
	@Autowired
	private A3ActiveStatusHander actStatusHandler;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			Matcher matcher = PATH_PATTERN.matcher(request.getServletPath());
	        if (matcher.matches() == false) {
	        	//Nothing process.
	        	return true;
	        }
	        
	        String sysId = matcher.group(1);
	        actStatusHandler.updateLastActiveTime(sysId);
		} catch(Throwable t) {
			log.error("The interceptor that record last A3 active time failed.", t);
		}
		
		return true;
	}
}
