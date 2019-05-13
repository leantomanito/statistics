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

import com.hh.db.TacticsDB;
import com.hh.entry.SysCache;
import com.hh.entry.TacticsChannel;
import com.hh.entry.TacticsTCP;
import com.hh.jdbc.JdbcUtil;
import com.hh.thread.TcpRunnable;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TacticsFlowJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(TacticsTCP.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        TacticsDB tacticsDB = new TacticsDB();
        List<TacticsTCP> tacticsTCPS = new ArrayList<>();
//        tacticsTCPS = tacticsDB.getLinkMsg();
        for (int i = 0; i < 10; i++) {
            tacticsTCPS.add(new TacticsTCP("10.10.15.224", 6011, "4.31"));
        }

        for (TacticsTCP tacticsTCP : tacticsTCPS) {
            cachedThreadPool.execute(new TcpRunnable(tacticsTCP));
        }
        //结束线程池内的所有线程任务
        cachedThreadPool.shutdown();
        while (true) {
            if (cachedThreadPool.isTerminated()) {
                log.info("线程池所有任务都已结束");
                break;
            }
        }

        for (Long aLong : SysCache.flowDataMap.keySet()) {
            SysCache.flowDataMap.get(aLong);
        }
    }
}
