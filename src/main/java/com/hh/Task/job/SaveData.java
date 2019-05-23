/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: SaveData.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月14日, Create
 */
package com.hh.Task.job;

import com.hh.db.TacticsDB;
import com.hh.entry.Board;
import com.hh.entry.EntryFlow;
import com.hh.entry.CommData;
import com.hh.entry.TacticsFlow;
import com.hh.util.DateUtil;

import java.util.*;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class SaveData {
    private static final String PREFIX_NAME = "td_tactics_5min_d_";

    public void saveFlow() {
        long endTime = DateUtil.getReorganizeMinute();
        long startTime = endTime - 300;
        ArrayList<EntryFlow> entryFlows = new ArrayList<>();
        Iterator<Integer> iterator = CommData.boardFlowMap.keySet().iterator();
        //整理数据
        while (iterator.hasNext()) {
            Integer id = iterator.next();
            Board board = CommData.boardFlowMap.get(id);
            LinkedList<TacticsFlow> tacticsFlows = board.getTacticsFlows();
            Map<Integer,TacticsFlow>  mergeFlowMap = new HashMap<>();
            for (TacticsFlow tacticsFlow : tacticsFlows) {
                if (tacticsFlow.getTime() >= startTime && tacticsFlow.getTime() < endTime) {
                    TacticsFlow mergeFlow = new TacticsFlow();
                    if(!mergeFlowMap.containsKey(tacticsFlow.getPolicyId())){
                        mergeFlowMap.put(tacticsFlow.getPolicyId(), mergeFlow);
                    }else{
                        mergeFlow = mergeFlowMap.get(tacticsFlow.getPolicyId());
                    }
                    mergeFlow.setPolicyId(tacticsFlow.getPolicyId());
                    mergeFlow.setTime(endTime);
                    //最大（上下）流量
                    if (tacticsFlow.getUpBps() > mergeFlow.getUpMaxBps()) {
                        mergeFlow.setUpMaxBps(tacticsFlow.getUpBps());
                    }
                    if (tacticsFlow.getDnBps() > mergeFlow.getDnMaxBps()) {
                        mergeFlow.setDnMaxBps(tacticsFlow.getDnBps());
                    }
                    //最小（上下）流量
                    if (tacticsFlow.getUpBps() < mergeFlow.getUpMinBps()) {
                        mergeFlow.setUpMinBps(tacticsFlow.getUpBps());
                    }
                    if (tacticsFlow.getDnBps() < mergeFlow.getDnMinBps()) {
                        mergeFlow.setDnMinBps(tacticsFlow.getDnBps());
                    }
                    mergeFlow.setDnBps(mergeFlow.getDnBps() + tacticsFlow.getDnBps());
                    mergeFlow.setUpBps(mergeFlow.getUpBps() + tacticsFlow.getUpBps());
                    mergeFlow.setUpDisBps(mergeFlow.getUpDisBps() + tacticsFlow.getUpDisBps());
                    mergeFlow.setDnDisBps(mergeFlow.getDnDisBps() + tacticsFlow.getDnDisBps());
                    mergeFlow.setDnWhiteBps(mergeFlow.getDnWhiteBps() + tacticsFlow.getDnWhiteBps());
                    mergeFlow.setUpWhiteBps(mergeFlow.getUpWhiteBps() + tacticsFlow.getUpWhiteBps());
                }
            }
            if(!tacticsFlows.isEmpty()){
                EntryFlow entryFlow = new EntryFlow(board, mergeFlowMap);
                entryFlows.add(entryFlow);
            }
        }
        //数据入库
        if(!entryFlows.isEmpty()){
            String tableName = PREFIX_NAME + DateUtil.tableSummaryTime();
            TacticsDB tacticsDB = new TacticsDB();

            if (!tacticsDB.isExistTable(tableName)) {
                tacticsDB.createTable(tableName);
            }
            tacticsDB.addSatics(tableName, entryFlows);
        }
    }
}
