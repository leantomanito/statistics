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
import com.hh.Task.job.SaveData;
import com.hh.entry.CommData;
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
public class IntegrationFlowJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(IntegrationFlowJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("5分钟执行一次" + System.currentTimeMillis());
        CommData.LOCK.lock();
        try{
            //统计数据将数据入库
            new SaveData().saveFlow();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //清空数据
            CommData.boardFlowMap.clear();
            CommData.CONDITION.signal();
            CommData.LOCK.unlock();
        }


    }
}
