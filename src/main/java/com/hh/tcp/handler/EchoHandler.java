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

import com.hh.entry.CommData;
import com.hh.entry.TacticsFlow;
import com.hh.entry.Board;
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

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class EchoHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger log = LoggerFactory.getLogger(EchoHandler.class);
    private Board board;

    public EchoHandler(Board board) {
        this.board = board;
    }

    //客户端与服务端创建连接的时候调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("IP：" + board.getIp() + ":" + board.getPort() + "-->链接成功");
        StringBuilder sb = new StringBuilder();
        sb.append(board.feelOutMsg() + "\r\n");
        ChannelFuture channelFuture = ctx.channel().writeAndFlush(Unpooled.copiedBuffer(sb.toString(), CharsetUtil.UTF_8));
        if (channelFuture.isSuccess()) {
            log.info("探测信息发送成功");
        } else {
            log.error("探测信息发送失败");
        }

    }

    //客户端与服务端断开连接的时候调用
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.error("IP：" + board.getIp() + ":" + board.getPort() + "-->掉线了...");
    }

    //服务端接收客户端发送过来的数据结束之后调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        log.info("IP：" + board.getIp() + ":" + board.getPort() + "-->完成接收信息...");
    }

    //工程出现异常的时候调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    //该类是核心方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {

        log.info("返回结果 :\n" + msg.toString());

        if (msg.toString().equals(board.feelOutMsg())) {
            Channel channel = ctx.channel();
            StringBuilder sb = new StringBuilder();
            sb.append("Begin getReaderInfo\r\n");
            sb.append("mTupleCtrlStatis,18\r\n");
            sb.append("End getReaderInfo items=1\r\n");
            channel.writeAndFlush(Unpooled.copiedBuffer(sb.toString(), CharsetUtil.UTF_8));
        } else if (msg.toString().indexOf(",") != -1) {
            long minute = DateUtil.getReorganizeMinute();
            if (!CommData.boardFlowMap.containsKey(board.getId())) {
                CommData.boardFlowMap.put(board.getId(), board);
            }
            TacticsFlow tacticsFlow = dataHandle(msg.toString());
            tacticsFlow.setTime(minute);
            Board hisBoard = CommData.boardFlowMap.get(board.getId());
            hisBoard.getTacticsFlows().add(tacticsFlow);
        } else if (msg.toString().indexOf("End") != -1) {
            ctx.close();
            log.info("tcp链接中断");
        } else if (msg.toString().indexOf("Begin") != -1) {

        } else {
            ctx.close();
            log.info("接受信息不合法，tcp链接中断");
        }
    }

    public TacticsFlow dataHandle(String msg) {
        if (StringUtils.isBlank(msg)) {
            return null;
        }
        String[] date = msg.split(",");
        TacticsFlow tacticsFlow = new TacticsFlow();
        tacticsFlow.setPolicyId(Integer.valueOf(date[0]));
        tacticsFlow.setUpBps(arrange32Data(Integer.valueOf(date[1]), Integer.valueOf(date[2])));
        tacticsFlow.setDnBps(arrange32Data(Integer.valueOf(date[3]), Integer.valueOf(date[4])));
        tacticsFlow.setUpDisBps(arrange32Data(Integer.valueOf(date[5]), Integer.valueOf(date[6])));
        tacticsFlow.setDnDisBps(arrange32Data(Integer.valueOf(date[7]), Integer.valueOf(date[8])));
        tacticsFlow.setUpWhiteBps(arrange32Data(Integer.valueOf(date[9]), Integer.valueOf(date[10])));
        tacticsFlow.setDnWhiteBps(arrange32Data(Integer.valueOf(date[11]), Integer.valueOf(date[12])));
        return tacticsFlow;
    }

    public Long arrange32Data(Integer hig, Integer low) {
        if (hig != null && low != null) {
            return (long) (hig * (1L << 32) + low) * 8 / 60;
        }
        return (long) 0;
    }

}
