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
package com.hh.tcp.handler;

import com.hh.entry.SysCache;
import com.hh.entry.TacticsChannel;
import com.hh.entry.TacticsFlow;
import com.hh.entry.TacticsTCP;
import com.hh.tcp.TcpClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class EchoHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger log = LoggerFactory.getLogger(TcpClient.class);
    private TacticsTCP tacticsTCP;

    public EchoHandler(TacticsTCP tacticsTCP) {
        this.tacticsTCP = tacticsTCP;
    }

    //客户端与服务端创建连接的时候调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info(ctx.channel().id().asLongText());

        log.info("IP：" + tacticsTCP.getIp() + ":" + tacticsTCP.getPort() + "-->链接成功");
        StringBuilder sb = new StringBuilder();
        sb.append("hello reader comm" + tacticsTCP.getCommver() + "\r\n");

        if (ctx.channel().isActive()) {
            ChannelFuture channelFuture = ctx.channel().writeAndFlush(Unpooled.copiedBuffer(sb.toString(), CharsetUtil.UTF_8));
            if (channelFuture.isSuccess()) {
                log.info("发送成功");
            } else {
                log.error("发送失败");
            }
        } else {
            log.info("该链接已经掉线");
        }
    }

    //客户端与服务端断开连接的时候调用
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        log.error("IP：" + tacticsTCP.getIp() + ":" + tacticsTCP.getPort() + "-->掉线了...");
        //使用过程中断线重连
//        final EventLoop eventLoop = ctx.channel().eventLoop();
//        eventLoop.schedule(new Runnable() {
//            @Override
//            public void run() {
//                Socket imConnection;
//                imConnection.connect(ImClientApp.HOST, ImClientApp.PORT);
//            }
//        }, 1L, TimeUnit.SECONDS);
//        super.channelInactive(ctx);
    }

    //服务端接收客户端发送过来的数据结束之后调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("IP：" + tacticsTCP.getIp() + ":" + tacticsTCP.getPort() + "-->完成接收信息...");
    }

    //工程出现异常的时候调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    //该类是核心方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf msgBuf = (ByteBuf) msg;
//        ByteBuf buf = msgBuf.readBytes(msgBuf.readableBytes());
        log.info("server response :\n" + msg.toString());
        if (msg.toString().equals(tacticsTCP.feelOutMsg())) {
            TacticsChannel tacticsChannel = new TacticsChannel(ctx.channel(), this.tacticsTCP);
            SysCache.sucChannelMap.put(ctx.channel().id().asLongText(), tacticsChannel);
        }
        if (msg.toString().indexOf(",") != -1) {
            dataHandle(msg.toString());
        }
    }

    public TacticsFlow dataHandle(String msg) {
        if (StringUtils.isBlank(msg)) {
            return null;
        }
        String[] date = msg.split(",");
        TacticsFlow tacticsFlow = new TacticsFlow();
        tacticsFlow.setPolicyId(Integer.valueOf(date[0]));

        return tacticsFlow;
    }

    public Long arrange32Data(Integer hig,Integer low){
        if(hig!=null && low!=null){
            return (long) (hig* (1L<<32) +low)*8/60;
        }
        return (long)0;
    }

}
