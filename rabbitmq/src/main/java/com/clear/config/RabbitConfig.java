package com.clear.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * ClassName RabbitConfig
 *
 * @author qml
 * Date 2022-4-29 17:45
 * Version 1.0
 **/
@Configuration
public class RabbitConfig {
    private static final String QUEUE_NAME = "myqueue";
    private static final String EXCHANGE_NAME = "xx";

    @Bean
    public Queue myQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange(EXCHANGE_NAME, false, false, null);
    }

    @Bean
    public Binding binding() {
        return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE_NAME, "direct.xx.ex", null);
    }
}