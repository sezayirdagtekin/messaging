package com.sezayir.demo.controller;



import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sezayir.demo.config.RabbitMQConfig;
import com.sezayir.demo.config.RabbitMqProperties;
import com.sezayir.demo.service.MessageSender;

@RestController
public class RabbitMQController {
	
	@Autowired
	private MessageSender messageSender;

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private RabbitMqProperties properties;
	
	@PostMapping("/send/message")
	public  ResponseEntity<String>  sendMessage(@RequestBody String message) {
		
		messageSender.sendMessage(rabbitTemplate, properties.getExchange(), message, properties.getRoutingKey());
		
		return new ResponseEntity<String>("Message sent:"+message, HttpStatus.OK);
		
	}

}
