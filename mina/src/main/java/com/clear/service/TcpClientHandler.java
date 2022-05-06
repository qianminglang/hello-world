package com.clear.service;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * ClassName TcpClientHandler
 *
 * @author qml
 * Date 2022-5-6 11:11
 * Version 1.0
 **/

public class TcpClientHandler extends IoHandlerAdapter {
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("session created");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        for (int i = 0; i < 10000; i++) {
            session.write("hello==" + i);
        }
    }


    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("收到消息：" + message.toString());
        session.write("recived:" + message.toString());
    }
}