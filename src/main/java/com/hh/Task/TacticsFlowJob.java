/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: TacticsFlowJob.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月09日, Create
 */
package com.hh.Task;

import com.hh.entry.SysCache;
import com.hh.entry.TacticsChannel;
import com.hh.entry.TacticsTCP;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TacticsFlowJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(TacticsTCP.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if(SysCache.sucChannelMap.isEmpty()){
            logger.info("暂无链接数据");
            return;
        }
        for(String key : SysCache.sucChannelMap.keySet()){
            TacticsChannel tacticsChannel = SysCache.sucChannelMap.get(key);
            Channel channel = tacticsChannel.getChannel();
            StringBuilder sb=new StringBuilder();
            sb.append("Begin getReaderInfo\r\n");
            sb.append("mTupleCtrlStatis,18\r\n");
            sb.append("End getReaderInfo items=1\r\n");
            channel.writeAndFlush(Unpooled.copiedBuffer(sb.toString(), CharsetUtil.UTF_8));
        }
    }
}
