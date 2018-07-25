package com.aerohive.nms.a3.communicator.handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aerohive.nms.a3.communicator.common.SynRequestCache;
import com.aerohive.nms.a3.message.A3ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SynMessageResultListener implements MessageListener {
	
	@Autowired
	private SynRequestCache synCache;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onMessage(Message message) {
		byte[] bs = message.getBody();
		String content = null;
		try {
			content = new String(bs, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("Translate body failed.", e);
		}
		System.out.println(content);
		
		A3ResponseMessage respMsg = null;
		try {
			respMsg = objectMapper.readValue(content, A3ResponseMessage.class);
		} catch (IOException e) {
			log.error("Translate body to A3ResponseMessage failed.", e);
		}
		
		if (respMsg != null) {
			synCache.notify(respMsg.getSequenceId(), content);
		}
	}

}
