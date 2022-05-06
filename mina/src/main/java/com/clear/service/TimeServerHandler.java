package com.clear.service;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

/**
 * ClassName TimeServerHandler
 *
 * @author qml
 * Date 2022-5-6 10:28
 * Version 1.0
 **/

public class TimeServerHandler extends IoHandlerAdapter {

    private static int count = 0;

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String mes = message.toString();
        if (mes.trim().equalsIgnoreCase("quit") || count > 10000) {
            System.out.println("正在退出时间服务器");
            session.write("正在退出时间服务器。。。。");
            session.close();
            return;
        }

        Date date = new Date();
        session.write(date.toString());
        System.out.println("message write:" + mes);
        count++;
    }
}