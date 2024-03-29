package com.sezayir.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Autowired
	private RabbitMqProperties properties;

	@Bean
	public TopicExchange getExchangeName() {
		return new TopicExchange(properties.getExchange());
	}

	@Bean
	public Queue getQueueName() {
		return new Queue(properties.getQueue());
	}

	@Bean
	public Binding declareBinding() {
		return BindingBuilder.bind(getQueueName()).to(getExchangeName())
				.with(properties.getRoutingKey());
	}

	@Bean
	public Jackson2JsonMessageConverter getMessageConverter() {
		return new Jackson2JsonMessageConverter();

	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory factory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
		rabbitTemplate.setMessageConverter(getMessageConverter());
		return rabbitTemplate;
	}




}
