/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: HelloHandler.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月08日, Create
 */
package com.hh.entry.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class HelloHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ServerHandler receive msg:"+msg.toString());

        //写消息：先得到channel，在写如通道然后flush刷新通道把消息发出去。
        ctx.channel().writeAndFlush("this is ServerHandler reply msg happend at !"+System.currentTimeMillis());
    }
}
