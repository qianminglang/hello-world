package com.clear.product;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * ClassName HelloWorldSender
 *
 * @author qml
 * Date 2022-4-28 15:59
 * Version 1.0
 **/

public class HelloWorldSender {
    /*** Rabbitmq是一个消息broker：接收消息，传递给下游应用**
     * 术语：
     * Producing就是指发送消息，发送消息的程序是Producer*
     * Queue指的是RabbitMQ内部的一个组件，消息存储于queue中。queue使用主机的内存和磁盘存储，收到内存和磁盘空间的限制*
     * 可以想象为一个大的消息缓冲。很多Producer可以向同一个queue发送消息，很多消费者可以从同一个queue消费消息。
     * Consuming就是接收消息。一个等待消费消息的应用程序称为Consumer**
     * 生产者、消费者、队列不必在同一台主机，一般都是在不同的主机上的应用。一个应用可以同时是生产者和消费者。**/

    public static void main(String[] args) {
        String QUEUE_NAME = "hello";

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.140.18.200");
//        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("123456");
        connectionFactory.setPort(5672);

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            // 声明一个队列
            // 第一个参数是队列名称，
            // 第二个参数false表示在rabbitmq-server重启后就没有了
            // 第三个参数表示该队列不是一个排外队列，否则一旦客户端断开，队列就删除了
            // 第四个参数表示该队列是否自动删除，true表示一旦不使用了，系统删除该队列
            // 第五个参数表示该队列的参数，该参数是Map集合，用于指定队列的属性
            channel.queueDeclare(QUEUE_NAME, false, false, true, null);
            String message = "hello world";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}