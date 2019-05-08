/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: TcpServer.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月08日, Create
 */
package com.hh.entry.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TcpServer2 {
    private  int port;
    public  void init(){
        System.out.println("正在启动tcp服务器……");
        NioEventLoopGroup boss = new NioEventLoopGroup();//主线程组
        NioEventLoopGroup work = new NioEventLoopGroup();//工作线程组
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();//引导对象
            bootstrap.group(boss,work);//配置工作线程组
            bootstrap.channel(NioServerSocketChannel.class);//配置为NIO的socket通道
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {//绑定通道参数
                    ch.pipeline().addLast("logging", new LoggingHandler("DEBUG"));//设置log监听器，并且日志级别为debug，方便观察运行流程
                    ch.pipeline().addLast("StringDecoder",new StringDecoder());
                    ch.pipeline().addLast("StringEncoder",new StringEncoder());
                    ch.pipeline().addLast("handler",new HelloHandler());//业务处理类，最终的消息会在这个handler中进行业务处理
                }
            });
            bootstrap.option(ChannelOption.SO_BACKLOG,1024);//缓冲区
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);//ChannelOption对象设置TCP套接字的参数，非必须步骤
            ChannelFuture future = bootstrap.bind(port).sync();//使用了Future来启动线程，并绑定了端口
            System.out.println("启动tcp服务器启动成功，正在监听端口:"+port);
            future.channel().closeFuture().sync();//以异步的方式关闭端口

        }catch (InterruptedException e) {
            System.out.println("启动出现异常："+e);
        }finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();//出现异常后，关闭线程组
            System.out.println("tcp服务器已经关闭");
        }

    }

    public static void main(String[] args) {
        new TcpServer2(8779 ).init();

    }
    public TcpServer2(int port) {
        this.port = port;
    }
}
