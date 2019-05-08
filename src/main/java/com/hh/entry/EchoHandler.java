/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: EchoHandler.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月08日, Create
 */
package com.hh.entry;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class EchoHandler extends SimpleChannelInboundHandler<Object> {
    //客户端与服务端创建连接的时候调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("能执行到这里");
        User user = TcpClient.channelUserMap.get(ctx.channel());
        ctx.writeAndFlush("测试消息"+user.getAge());
    }

    //客户端与服务端断开连接的时候调用
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(1);
        super.channelInactive(ctx);
    }

    //服务端接收客户端发送过来的数据结束之后调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println(2);
        super.channelReadComplete(ctx);
    }

    //工程出现异常的时候调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(3);
        super.exceptionCaught(ctx, cause);
    }

    //该类是核心方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        String body = (String)o;
        System.out.println("server response :\n"+body);
    }
}
