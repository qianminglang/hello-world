package com.clear.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * ClassName DirectLogProducter
 *
 * @author qml
 * Date 2022-4-29 16:19
 * Version 1.0
 **/

public class DirectLogProducter {
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

        String routingKey = "";

        for (int i = 0; i < 1000; i++) {
            switch (i % 3) {
                case 0:
                    routingKey = "bug";
                    break;
                case 1:
                    routingKey = "info";
                    break;
                case 2:
                    routingKey = "error";
                    break;
            }
            String message = "这是【" + routingKey + "】的消息";
            System.out.println("发送："+message);
            channel.basicPublish(exchageName, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
        }
    }
}