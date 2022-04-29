package com.clear.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * ClassName LogTopic
 *
 * @author qml
 * Date 2022-4-29 16:49
 * Version 1.0
 **/

public class LogTopic {
    private static final String EXCHANGE_NAME = "topic_logs";
    private static final String[] SPEED = {"lazy", "quick", "normal"};
    private static final String[] COLOR = {"black", "orange", "red", "yellow", "blue", "white", "pink"};
    private static final String[] SPECIES = {"dog", "rabbit", "chicken", "horse", "bear", "cat"};
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.140.18.200");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        for (int i = 0; i < 10; i++) {
            String speed = SPEED[RANDOM.nextInt(SPEED.length)];
            String color = COLOR[RANDOM.nextInt(COLOR.length)];
            String species = SPECIES[RANDOM.nextInt(SPECIES.length)];
            String message = speed + "-" + color + "-" + species;
            String routingkeys = speed + "." + color + "." + species;
            channel.basicPublish(EXCHANGE_NAME, routingkeys, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者：" + message);
        }
    }
}