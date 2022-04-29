package com.clear.task;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * ClassName Worker
 *
 * @author qml
 * Date 2022-4-29 10:06
 * Version 1.0
 **/

public class Worker {
    private static final String QUEUE_NAME = "taskQueue";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.140.18.200");
//        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(1);
            boolean autoAck = true;

            // 声明一个队列
            // 第一个参数是队列名称，
            // 第二个参数false表示在rabbitmq-server重启后就没有了
            // 第三个参数表示该队列不是一个排外队列，否则一旦客户端断开，队列就删除了
            // 第四个参数表示该队列是否自动删除，true表示一旦不使用了，系统删除该队列
            // 第五个参数表示该队列的参数，该参数是Map集合，用于指定队列的属性
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);


            while (true) {
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                    doWork(message);
                    System.out.println("message:" + message);
                };
                channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

    }

    private static void doWork(String message) {
        for (char ch : message.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}