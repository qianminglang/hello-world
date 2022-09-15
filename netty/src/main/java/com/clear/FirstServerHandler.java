package com.clear;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * ClassName FirstServerHandler
 *
 * @author qml
 * Date 2022-5-6 16:39
 * Version 1.0
 **/

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println( byteBuf.toString(StandardCharsets.UTF_8));
        ByteBuf out = getOut(ctx);
        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getOut(ChannelHandlerContext ctx) {
        byte[] bytes = "【服务器】：我是服务器，我收到你的消息了！".getBytes(StandardCharsets.UTF_8);
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        return buffer;
    }
}