package com.clear.service;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * ClassName TcpClient
 *
 * @author qml
 * Date 2022-5-6 11:06
 * Version 1.0
 **/

public class TcpClient {
    public static void main(String[] args) {
        //创建Ioservice实例
        NioSocketConnector connector = new NioSocketConnector();
        //设置过滤链
        connector.getFilterChain().addLast("logger",new LoggingFilter());
        connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(StandardCharsets.UTF_8)));

        //设置处理器
        connector.setHandler(new TcpClientHandler());

        connector.connect(new InetSocketAddress("localhost",9123));
    }
}