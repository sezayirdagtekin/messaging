package com.sezayir.demo.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.sezayir.demo.model.Person;

@Service
public class RabbitMqListener {
	

	@RabbitListener(queues="${rabbitmq.queue}")
	public void listenMessage(Person person) {
	System.out.println("Message received:"+ person.toString());
	}

}
