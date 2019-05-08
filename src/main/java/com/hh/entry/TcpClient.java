/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: TcpClient.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月08日, Create
 */
package com.hh.entry;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TcpClient {
    private String ip;
    private int port;
    public static Map<Channel,User> channelUserMap = new HashMap<>();
    public  void init() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.TCP_NODELAY,true);
            bootstrap.handler(new ChannelInitializer() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast("logging",new LoggingHandler("DEBUG"));
                    ch.pipeline().addLast("StringDecoder",new StringDecoder());
                    ch.pipeline().addLast("StringEncoder",new StringEncoder());
                    ch.pipeline().addLast("ClientHandler",new EchoHandler());
                }
            });
            ChannelFuture future = bootstrap.connect(ip,port);
            ChannelFuture future1 = bootstrap.connect(ip,8779);
            channelUserMap.put(future.channel(),new User("张三",123));
            channelUserMap.put(future1.channel(),new User("李四",456));
//            channelUserMap.put(future2.channel(),new User("王五",789));
            future.channel().closeFuture().sync();
            future1.channel().closeFuture().sync();
            /*future2.channel().closeFuture().sync();*/
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public TcpClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        new TcpClient("127.0.0.1",8778).init();
    }
}
