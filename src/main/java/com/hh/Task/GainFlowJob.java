/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: GainFlowJob.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月09日, Create
 */
package com.hh.Task;

import com.hh.Task.job.BuildTcpConnect;
import com.hh.entry.Board;
import com.hh.entry.CommData;
import com.hh.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class GainFlowJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(Board.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("一分钟执行一次" + System.currentTimeMillis());
        CommData.LOCK.lock();
        try {
            //如果在5分钟的时候让改线程挂起执行统计任务
            if (DateUtil.getReorganizeMinute() % 300 == 0 && !CommData.boardFlowMap.isEmpty()) {
                    CommData.CONDITION.await();
            }
            if(CommData.boardFlowMap.isEmpty())
                BuildTcpConnect.getBoardMsg();
            //建立连接
            log.info("本次连接主机数量-----\n"+CommData.boardFlowMap.size());
            new BuildTcpConnect().allBuild();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            CommData.LOCK.unlock();
        }
    }

}
