package com.sezayir.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitMQConfig {
	@Value("${rabbitmq.exchange}")
	private String exchange;

	@Value("$rabbitmq.queue}")
	private String queue;

	@Value("${rabbitmq.routingKey}")
	private String routingKey;
	

	@Bean
	public TopicExchange getExchangeName() {
		return new TopicExchange(exchange);
	}

	@Bean
	public Queue getQueueName() {
		return new Queue(queue);
	}

	@Bean
	public Binding declareBinding() {
		return BindingBuilder.bind(getQueueName()).to(getExchangeName())
				.with(routingKey);
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

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}

	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public String getQueue() {
		return queue;
	}

	public String getExchange() {
		return exchange;
	}

}
