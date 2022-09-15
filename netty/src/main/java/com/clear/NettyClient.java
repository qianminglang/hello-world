package com.clear;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * ClassName NettyClient
 *
 * @author qml
 * Date 2022-5-6 14:54
 * Version 1.0
 **/

public class NettyClient {
    private static final int MAX_RETRY = 5;
    private static final String host = "127.0.0.1";
    private static final int port = 8000;

    public static void main(String[] args) {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        //指定线程模型
        bootstrap.group(workerGroup)
                //指定IO类型为NIO
                .channel(NioSocketChannel.class)
                //处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //解码一定要放在第一个，在这里pipeline按顺序执行，不然接收消息无法正常使用
//                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,7,4));
//                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
//                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        //连接
        connect(bootstrap, host, port, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        //建立连接
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            } else if (retry == 0) {
                System.out.println("重试次数已用完，放弃连接！");
            } else {
                //第几次重连
                int order = (MAX_RETRY - retry) + 1;
                //北次重连的间隔1<<order相当于1乘以2的order次方
                int delay = 1 << order;
                System.out.println("第" + order + "次重连,重连间隔：" + delay + "秒");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }
}