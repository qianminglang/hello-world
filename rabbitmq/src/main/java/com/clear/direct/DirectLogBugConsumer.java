package com.clear.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * ClassName DirectLogConsumer
 *
 * @author qml
 * Date 2022-4-29 16:26
 * Version 1.0
 **/

public class DirectLogBugConsumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        String exchageName = "direct_log";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.140.18.200");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchageName, BuiltinExchangeType.DIRECT);

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchageName, "bug");

        DeliverCallback deliverCallback = ((consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(delivery.getEnvelope().getRoutingKey() + "接收:" + message);
        });

        channel.basicConsume(queueName, deliverCallback, consumerTag -> {
        });
    }
}