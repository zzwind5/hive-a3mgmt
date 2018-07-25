package com.aerohive.nms.a3.communicator.handler;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aerohive.nms.a3.communicator.config.RabbitMQConfig;
import com.aerohive.nms.a3.message.A3ResponseMessage;

@Component
public class MessageSendHandler {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void broadcastMessageResult(A3ResponseMessage respMsg) {
		this.rabbitTemplate.convertAndSend(RabbitMQConfig.SYN_RESULT_BROADCAST_EXCHANGE, "", respMsg);
	}
}