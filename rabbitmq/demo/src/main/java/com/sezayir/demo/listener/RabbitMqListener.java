package com.sezayir.demo.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqListener {
	

	@RabbitListener(queues="${rabbitmq.queue}")
	public void listenMessage(String message) {
		System.out.println("Message received:"+ message);
	}

}
