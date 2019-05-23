/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: TcpConnect.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月09日, Create
 */
package com.hh.runner;

import com.hh.entry.Board;
import com.hh.tcp.filter.ClientFilter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TcpConnect implements Runnable {
    private Board board;
    private static final Logger log = LoggerFactory.getLogger(TcpConnect.class);
    private static final NioEventLoopGroup group = new NioEventLoopGroup();
    public TcpConnect(Board board) {
        this.board = board;
    }

    //监听如果程序异常退出关闭线程池
    static {
        destroyGroup();
    }

    @Override
    public synchronized void run() {
        connect();
    }

    public void connect(){
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            //连接超时设置
            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.handler(new ClientFilter(board));
            ChannelFuture future = bootstrap.connect(board.getIp(), board.getPort());
            future.channel().closeFuture().sync();
            if(!future.isSuccess()){
                log.error("IP：" + board.getIp() + ":" + board.getPort() + "-->链接失败...");
                future.channel().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void destroyGroup(){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try {
                    group.shutdownGracefully().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
