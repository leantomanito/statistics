/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: SelfTask.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月09日, Create
 */
package com.hh.Task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class SelfTask {
    /**
     * 定时调度任务
     */
    private static final SchedulerFactory sf = new StdSchedulerFactory();
    private static final String JOB_TACTICS_FLOW = "tacticsFlow";
    private static final String JOBGROUP_TACTICS_FLOWS = "tacticsFlows";
    private static final String JOB_INTEGRATION_FLOW = "integrationFlow";
    private static final String JOBGROUP_INTEGRATION_FLOWS = "integrationFlows";
    private static final String TRIGGER_TACTICS_FLOW = "tigTacticsFlow";
    private static final String TRIGGER_INTEGRATION_FLOW = "tigIntegrationFlow";

    public void start() {
        Scheduler scheduler = null;
        try {
//		通过schedulerFactory获取一个调度器
            scheduler = this.sf.getScheduler();

//		 创建jobDetail实例，绑定Job实现类
//		 指明job的名称，所在组的名称，以及绑定job类
            JobDetail tacticsFlowJob = JobBuilder.newJob(TacticsFlowJob.class).withIdentity(JOB_TACTICS_FLOW, JOBGROUP_TACTICS_FLOWS).build();
            JobDetail integrationFlowJob = JobBuilder.newJob(IntegrationFlowJob.class).withIdentity(JOB_INTEGRATION_FLOW, JOBGROUP_INTEGRATION_FLOWS).build();


//		 定义调度触发规则

//		使用simpleTrigger规则
            Trigger tacticsFlowTrigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_TACTICS_FLOW, TRIGGER_TACTICS_FLOW)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
                    .startNow().build();
            Trigger integrationFlowTrigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_INTEGRATION_FLOW, TRIGGER_INTEGRATION_FLOW)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ?"))
                    .startNow().build();
//		 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(tacticsFlowJob, tacticsFlowTrigger);
            scheduler.scheduleJob(integrationFlowJob, integrationFlowTrigger);
//		 启动调度
            scheduler.start();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//
//    public static void main(String[] args) {
//        new SelfTask().start();
//    }
}
