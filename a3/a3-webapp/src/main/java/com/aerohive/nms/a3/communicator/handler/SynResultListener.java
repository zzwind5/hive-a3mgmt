package com.aerohive.nms.a3.communicator.handler;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "A3.message.result.fanout.1C39479B66FE11")
public class SynResultListener {

//	@RabbitHandler
    public void process(Object respMsg) {
        System.out.println("FanoutReceiver  : " + respMsg);
    }
}
