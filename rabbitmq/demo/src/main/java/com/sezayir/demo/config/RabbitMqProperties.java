package com.sezayir.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqProperties {
	
	@Value("${rabbitmq.exchange}")
	private String exchange;

	@Value("${rabbitmq.queue}")
	private String queue;

	@Value("${rabbitmq.routingKey}")
	private String routingKey;
	

	public String getExchange() {
		return exchange;
	}

	public String getQueue() {
		return queue;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	
	
}
