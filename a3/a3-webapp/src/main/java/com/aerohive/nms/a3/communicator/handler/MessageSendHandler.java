package com.aerohive.nms.a3.communicator.handler;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aerohive.nms.a3.communicator.common.SynRequestCache;
import com.aerohive.nms.a3.communicator.config.RabbitMQConfig;
import com.aerohive.nms.a3.message.A3ResponseMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageSendHandler {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	
	public void broadcastMessageResult(A3ResponseMessage respMsg) {
		try {
			this.rabbitTemplate.convertAndSend(RabbitMQConfig.SYN_RESULT_BROADCAST_EXCHANGE, 
					"", objectMapper.writeValueAsString(respMsg));
		} catch (AmqpException | JsonProcessingException e) {
			log.error("Broadcast synchronized message response failed.", e);
		}
	}
}