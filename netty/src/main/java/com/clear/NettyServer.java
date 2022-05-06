package com.clear;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 * ClassName NettyServer
 *
 * @author qml
 * Date 2022-5-6 14:15
 * Version 1.0
 **/


public class NettyServer {
    public static void main(String[] args) {
        /**
         * ServerBootstrap是一个引导类，其对象的作用是引导服务器的启动工作。
         * .group是配置上面两个线程组的角色，也就是谁去监听谁去处理读写。上面只是创建了两个线程组，并没有实际使用。
         * .channel是配置服务端的IO模型，上面代码配置的是NIO模型。也可以配置为BIO，如OioServerSocketChannel.class。
         * .childHandler用于配置每条连接的数据读写和业务逻辑等。上面代码用的是匿名内部类，并没有什么内容。实际使用中为了规范起见，一般会再写一个单独的类也就是初始化器，在里面写上需要的操作。就如Netty实战那篇中的代码一样。
         * 最后就是绑定监听端口了。
         * 　　引导类最小化的参数配置就是如上四个：配置线程组、IO模型、处理逻辑、绑定端口。
         **/
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
            protected void initChannel(NioSocketChannel ch) {
                ch.pipeline().addLast(new FirstServerHandler());
            }
        });


        //1.handler（）方法：上面的cildHandler是处理连接的读写逻辑，这个是用于指定服务端启动中的逻辑.
        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
            @Override
            protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                System.out.println("服务端启动中.....");
            }
        });

        //2.attr（）方法：给服务端的channel指定一些自定义属性。然后通过channel.attr（）取出这个属性，其实就是给channel维护一个map。一般也用不上。
        serverBootstrap.attr(AttributeKey.valueOf("ssc.key"), "scc.value");

        //3.childAttr（）方法：作用和上面一样，这个是针对客户端的channel。
        serverBootstrap.childAttr(AttributeKey.valueOf("client"), "value");

        //给服务端的channel设置属性
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);

        //这个是给每条客户端连接设置TCP相关的属性
        //开启TCP底层心跳机制
        //开启nagle算法，如果要求实时性高，有数据发送时就立马发送，就关闭，如果需要减少发送次数减少网络交互就开启
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.childOption(ChannelOption.TCP_NODELAY, false);

        serverBootstrap.bind(8000);
    }
}