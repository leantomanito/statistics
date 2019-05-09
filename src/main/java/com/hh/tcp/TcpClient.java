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
package com.hh.tcp;

import com.hh.Task.SelfTask;
import com.hh.entry.SysCache;
import com.hh.entry.TacticsChannel;
import com.hh.entry.TacticsTCP;
import com.hh.tcp.handler.EchoHandler;
import com.hh.thread.TcpRunnable;
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
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TcpClient {
    private static final Logger log = LoggerFactory.getLogger(TcpClient.class);
    private TacticsTCP tacticsTCP;

    public TcpClient(TacticsTCP tacticsTCP) {
        this.tacticsTCP = tacticsTCP;
    }
    public void init(){
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast("logging", new LoggingHandler("DEBUG"));
                    ch.pipeline().addLast("StringDecoder", new StringDecoder());
                    ch.pipeline().addLast("StringEncoder", new StringEncoder());
                    ch.pipeline().addLast("ping", new IdleStateHandler(60, 20, 60 * 10, TimeUnit.SECONDS));
                    ch.pipeline().addLast("ClientHandler", new EchoHandler());
                }
            });
            ChannelFuture future = bootstrap.connect(tacticsTCP.getIp(), tacticsTCP.getPort());
            SysCache.channelMap.put(future.channel().id().asLongText(),
                    new TacticsChannel(future.channel(), tacticsTCP));
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
