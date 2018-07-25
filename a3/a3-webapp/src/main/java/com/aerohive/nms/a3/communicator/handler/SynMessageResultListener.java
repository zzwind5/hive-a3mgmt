package com.aerohive.nms.a3.communicator.handler;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class SynMessageResultListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("#######  " + message);
	}

}
