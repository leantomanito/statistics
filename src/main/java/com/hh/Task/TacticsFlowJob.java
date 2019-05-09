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
import io.netty.channel.Channel;
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
        if(SysCache.channelMap.isEmpty()){
            logger.info("暂无链接数据");
            return;
        }
        for(String key : SysCache.channelMap.keySet()){
            TacticsChannel tacticsChannel = SysCache.channelMap.get(key);
            Channel channel = tacticsChannel.getChannel();
            if(!channel.isActive()){
                logger.info(key+"没有链接成功");
                continue;
            }
            TacticsTCP tacticsTCP = tacticsChannel.getTacticsTCP();
            channel.writeAndFlush("我支持的协议号：" + tacticsTCP.getCommver());
        }
    }
}
