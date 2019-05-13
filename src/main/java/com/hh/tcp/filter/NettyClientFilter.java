/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: NettyClientFilter.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月10日, Create
 */
package com.hh.tcp.filter;

import com.hh.entry.TacticsTCP;
import com.hh.tcp.handler.EchoHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class NettyClientFilter extends ChannelInitializer {
    private TacticsTCP tacticsTCP;

    public NettyClientFilter(TacticsTCP tacticsTCP) {
        this.tacticsTCP = tacticsTCP;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
//        ch.pipeline().addLast("logging", new LoggingHandler("DEBUG"));
        ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        ch.pipeline().addLast("StringDecoder", new StringDecoder());
        ch.pipeline().addLast("StringEncoder", new StringEncoder());
//                    ch.pipeline().addLast("ping", new IdleStateHandler(60, 20, 60 * 10, TimeUnit.SECONDS));
        ch.pipeline().addLast("ClientHandler", new EchoHandler(tacticsTCP));
    }
}
