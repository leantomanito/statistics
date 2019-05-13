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
import com.hh.entry.TacticsFlow;
import com.hh.jdbc.JdbcUtil;
import com.hh.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.*;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class IntegrationFlowJob implements Job {
    private static final String PREFIX_NAME = "td_tactics_5min_d_";
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("5分钟执行一次");
        //统计数据将数据入库
        long endTime = DateUtil.getReorganizeMinute();
        long startTime = endTime - 300;
        Map<Integer, List<TacticsFlow>> listMap = new HashMap<>();
        Iterator<Long> iterator = SysCache.flowDataMap.keySet().iterator();
        while (iterator.hasNext()){
            Long aLong = iterator.next();
            if (aLong >= startTime && aLong < endTime) {
                List<TacticsFlow> cTacticsFlows = SysCache.flowDataMap.get(aLong);
                for (TacticsFlow tacticsFlow : cTacticsFlows) {
                    if(!listMap.containsKey(tacticsFlow.getPolicyId())){
                        listMap.put(tacticsFlow.getPolicyId(),new ArrayList<TacticsFlow>());
                    }
                    List<TacticsFlow> tacticsFlowList = listMap.get(tacticsFlow.getPolicyId());
                    tacticsFlowList.add(tacticsFlow);
                }
                iterator.remove();
            }
        }
        List<TacticsFlow> enterDatabase = new ArrayList<>();
        for (Integer integer : listMap.keySet()) {
            TacticsFlow mergeFlow = new TacticsFlow();
            mergeFlow.setPolicyId(integer);
            mergeFlow.setTime(endTime);
            List<TacticsFlow> tacticsFlows = listMap.get(integer);
            for (TacticsFlow tacticsFlow : tacticsFlows) {
                if(tacticsFlow.getUpBps()>mergeFlow.getUpMaxBps()){
                    mergeFlow.setUpMaxBps(tacticsFlow.getUpBps());
                }
                if(tacticsFlow.getDnBps()>mergeFlow.getDnMaxBps()){
                    mergeFlow.setDnMaxBps(tacticsFlow.getDnBps());
                }
                mergeFlow.setDnBps(mergeFlow.getDnBps()+tacticsFlow.getDnBps());
                mergeFlow.setUpBps(mergeFlow.getUpBps()+tacticsFlow.getUpBps());
            }
            enterDatabase.add(mergeFlow);
        }

        String tableName = PREFIX_NAME + DateUtil.tableSummaryTime();

        TacticsDB tacticsDB = new TacticsDB();

        if(!tacticsDB.isExistTable(tableName)){
            tacticsDB.createTable(tableName);
        }

        tacticsDB.addSatics(tableName, enterDatabase);
    }
}
