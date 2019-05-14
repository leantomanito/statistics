/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: ReadData.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月14日, Create
 */
package com.hh.Task.job;

import com.hh.db.TacticsDB;
import com.hh.entry.TacticsTCP;
import com.hh.thread.TcpRunnable;
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
public class ReadData {
    private static final Logger log = LoggerFactory.getLogger(ReadData.class);
    public void readerDataForDevice(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        TacticsDB tacticsDB = new TacticsDB();
        List<TacticsTCP> tacticsTCPS = new ArrayList<>();
//        tacticsTCPS = tacticsDB.getLinkMsg();
        for (int i = 0; i < 15; i++) {
            tacticsTCPS.add(new TacticsTCP("10.10.15.224", 6011, "4.31"));
//            tacticsTCPS.add(new TacticsTCP("127.0.0.1", 8000, "4.31"));
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

    }
}
