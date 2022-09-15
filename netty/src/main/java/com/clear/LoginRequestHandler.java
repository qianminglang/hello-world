package com.clear;

import com.clear.entity.LoginRequestPacket;
import com.clear.entity.PacketCodeC;
import com.clear.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * ClassName LoginRequestHandler
 *
 * @author qml
 * Date 2022-5-7 11:49
 * Version 1.0
 **/

public class LoginRequestHandler extends ChannelInboundHandlerAdapter {

    //channelActive客户端和服务端建立连接后调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10000; i++) {
            ByteBuf byteBuf = getByteBuf(ctx,i);
            ctx.channel().writeAndFlush(byteBuf);
        }
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx,int i) {
        //1.获取二进制抽象 byteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(i);
        loginRequestPacket.setUserName(String.valueOf(i));
        loginRequestPacket.setPassword(String.valueOf(i));
        byte[] serialize = Serializer.DEFAULT.serialize(loginRequestPacket);
        buffer.writeBytes(serialize);
        buffer.writeBytes("\r\n".getBytes());
        ByteBuf encode = PacketCodeC.INSTANCE.encode(buffer, loginRequestPacket);
        return encode;
    }


    //在接收到服务端的消息后调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString(StandardCharsets.UTF_8));
        System.out.println();
    }
}