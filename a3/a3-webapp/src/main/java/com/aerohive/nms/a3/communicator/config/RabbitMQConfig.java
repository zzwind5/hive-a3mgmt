package com.aerohive.nms.a3.communicator.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aerohive.nms.a3.communicator.handler.SynMessageResultListener;
import com.aerohive.nms.a3.communicator.util.A3CommunicatorUtil;

@Configuration
public class RabbitMQConfig {
	
	public static final String SYN_RESULT_BROADCAST_QUEUE = "A3.message.result.fanout." + A3CommunicatorUtil.getLocalMac();
//	public static final String SYN_RESULT_BROADCAST_QUEUE = "A3.message.result.fanout.1C39479B66FE11";
	public static final String SYN_RESULT_BROADCAST_EXCHANGE = "A3MessageResultFanoutExchange";

	@Bean
    public Queue messageResultQ() {
        return new Queue(SYN_RESULT_BROADCAST_QUEUE);
    }
	
	@Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(SYN_RESULT_BROADCAST_EXCHANGE);
    }
	
	@Bean
	public Binding bindingExchangeFanout(Queue messageResultQ, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(messageResultQ).to(fanoutExchange);
	}
	
	@Bean
	public SimpleMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(SYN_RESULT_BROADCAST_QUEUE);
		container.setMessageListener(new SynMessageResultListener());
		return container;
	}
}
