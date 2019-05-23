/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: BuildTcpConnect.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月14日, Create
 */
package com.hh.Task.job;

import com.hh.db.TacticsDB;
import com.hh.entry.Board;
import com.hh.entry.CommData;
import com.hh.runner.TcpConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class BuildTcpConnect {
    private static final Logger log = LoggerFactory.getLogger(BuildTcpConnect.class);
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public void allBuild() {
        for (Integer key : CommData.boardFlowMap.keySet()) {
            cachedThreadPool.execute(new TcpConnect(CommData.boardFlowMap.get(key)));
        }
    }

    public static void getBoardMsg() {
        TacticsDB tacticsDB = new TacticsDB();
        CommData.boardFlowMap.putAll(tacticsDB.getLinkMsg());
    }
}
