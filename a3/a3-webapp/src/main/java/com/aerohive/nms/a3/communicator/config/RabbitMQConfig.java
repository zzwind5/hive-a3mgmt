package com.aerohive.nms.a3.communicator.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aerohive.nms.a3.communicator.util.A3CommunicatorUtil;

@Configuration
public class RabbitMQConfig {

	@Bean
    public Queue messageResultQ() {
        return new Queue("A3.message.result.fanout." + A3CommunicatorUtil.getLocalMac());
    }
	
	@Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("A3MessageResultFanoutExchange");
    }
	
	@Bean
	public Binding bindingExchangeFanout(Queue messageResultQ, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(messageResultQ).to(fanoutExchange);
	}
}
