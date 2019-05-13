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
import com.hh.util.DateUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
        ctx.close();
    }

    //该类是核心方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf msgBuf = (ByteBuf) msg;
//        ByteBuf buf = msgBuf.readBytes(msgBuf.readableBytes());

        log.info("server response :\n" + msg.toString());


        if (msg.toString().equals(tacticsTCP.feelOutMsg())) {
            TacticsChannel tacticsChannel = new TacticsChannel(ctx.channel(), this.tacticsTCP);
//            SysCache.sucChannelMap.put(ctx.channel().id().asLongText(), tacticsChannel);
            Channel channel = ctx.channel();
            StringBuilder sb = new StringBuilder();
            sb.append("Begin getReaderInfo\r\n");
            sb.append("mTupleCtrlStatis,18\r\n");
            sb.append("End getReaderInfo items=1\r\n");
            channel.writeAndFlush(Unpooled.copiedBuffer(sb.toString(), CharsetUtil.UTF_8));
        }
        synchronized(EchoHandler.class){
            if (msg.toString().indexOf(",") != -1) {
                long minute = DateUtil.getReorganizeMinute();
                if(!SysCache.flowDataMap.containsKey(minute)){
                    SysCache.flowDataMap.put(minute, new ArrayList<TacticsFlow>());
                }
                TacticsFlow tacticsFlow = dataHandle(msg.toString());
                tacticsFlow.setTime(minute);
                List<TacticsFlow> tacticsFlows = SysCache.flowDataMap.get(minute);
                tacticsFlows.add(tacticsFlow);
            }
        }
        if(msg.toString().indexOf("End") != -1){
            ctx.close();
            log.info("tcp链接中断");
        }
    }

    public TacticsFlow dataHandle(String msg) {
        if (StringUtils.isBlank(msg)) {
            return null;
        }
        String[] date = msg.split(",");
        TacticsFlow tacticsFlow = new TacticsFlow();
        tacticsFlow.setPolicyId(Integer.valueOf(date[0]));
        tacticsFlow.setUpBps(arrange32Data(Integer.valueOf(date[1]),Integer.valueOf(date[2])));
        tacticsFlow.setDnBps(arrange32Data(Integer.valueOf(date[3]),Integer.valueOf(date[4])));
        return tacticsFlow;
    }

    public Long arrange32Data(Integer hig,Integer low){
        if(hig!=null && low!=null){
            return (long) (hig* (1L<<32) +low)*8/60;
        }
        return (long)0;
    }

}
