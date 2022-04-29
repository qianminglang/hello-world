package com.clear.product;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * ClassName HelloWorldConsumer
 *
 * @author qml
 * Date 2022-4-28 16:25
 * Version 1.0
 **/

public class HelloWorldConsumer {


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
            System.out.println(" [*] Waiting for messages. To exit pressCTRL+C");

            //消息推送回调函数
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.printf("message:" + message);
            };

            /*使用服务器生成的consumerTag启动本地，非排他的使用者。
            启动一个仅提供了basic.deliver和basic.cancel AMQP方法（对大多数情形够用了）
            第一个参数：队列名称autoAck – true
            只要服务器发送了消息就表示消息已经被消费者确认;
            false服务端等待客户端显式地发送确认消息deliverCallback – 服务端推送过来的消息回调函数
            cancelCallback – 客户端忽略该消息的回调方法Returns:服务端生成的consumerTag
            */
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}