package com.clear.service;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * ClassName MinaTimeServer
 *
 * @author qml
 * Date 2022-5-6 10:22
 * Version 1.0
 **/

public class MinaTimeServer {
    public static void main(String[] args) throws IOException {
        //创建IoService实例
        NioSocketAcceptor acceptor = new NioSocketAcceptor();

        //设置过滤链 日志编解码
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        //设置处理器 就是业务处理的地方
        acceptor.setHandler(new TimeServerHandler());

        //监听端口
        acceptor.bind(new InetSocketAddress(9123));

    }
}