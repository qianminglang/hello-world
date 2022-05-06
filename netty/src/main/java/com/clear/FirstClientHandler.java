package com.clear;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * ClassName FirstClientHandler
 *
 * @author qml
 * Date 2022-5-6 15:44
 * Version 1.0
 **/

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * channelActive()方法会在客户端与服务器建立连接后调用。所以我们可以在这里面编写逻辑代码
     * .alloc().buffer()的作用是把字符串的二进制数据填充到ByteBuf。
     * .writeBytes()的作用是把数据写到服务器。
     * channelRead()在接受到服务端的消息后调用。
     **/

    //客户端与服务器建立连接后调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 1000; i++) {
            System.out.println("客户端发送消息"+i);
            //1.获取数据
            ByteBuf byteBuf = getByteBuf(ctx);
            //2.写数据
            ctx.channel().writeAndFlush(byteBuf);
        }
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        //1.获取二进制抽象 byteBuf
        ByteBuf byteBuf = ctx.alloc().buffer();

        //2.准备数据，指定字符串的字符集为UTF-8
        byte[] bytes = ("【客户端】:这是客户端发送的消息：" + new Date()).getBytes(StandardCharsets.UTF_8);

        //3.填充数据到ByteBuf
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        //接受服务端的消息并打印
        System.out.println(byteBuf.toString(StandardCharsets.UTF_8));
        System.out.println();
    }
}