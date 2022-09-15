package com.clear;

import com.clear.entity.LoginResponsePacket;
import com.clear.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * ClassName LoginResponseHandler
 *
 * @author qml
 * Date 2022-5-7 11:48
 * Version 1.0
 **/

public class LoginResponseHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf= (ByteBuf) msg;
        System.out.println(byteBuf.toString(StandardCharsets.UTF_8));

        ByteBuf buffer = ctx.alloc().buffer();

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setSuccess(true);
        loginResponsePacket.setReason("成功");
        byte[] serialize = Serializer.DEFAULT.serialize(loginResponsePacket);
        buffer.writeBytes(serialize);
        ctx.channel().writeAndFlush(buffer);
    }
}