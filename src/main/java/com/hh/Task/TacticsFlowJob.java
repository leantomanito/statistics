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

import com.hh.Task.job.ReadData;
import com.hh.entry.SysCache;
import com.hh.entry.TacticsTCP;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TacticsFlowJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(TacticsTCP.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("一分钟执行一次" + System.currentTimeMillis());
        for (Long aLong : SysCache.flowDataMap.keySet()) {
            System.out.println("key-->" + aLong + "  size-->" + SysCache.flowDataMap.get(aLong).size());
        }
        new ReadData().readerDataForDevice();
    }
}
