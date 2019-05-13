package com.hh;

import com.hh.Task.SelfTask;
import com.hh.db.TacticsDB;
import com.hh.entry.TacticsFlow;
import com.hh.entry.TacticsTCP;
import com.hh.jdbc.JdbcUtil;
import com.hh.thread.TcpRunnable;
import com.hh.thread.TcpServerRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
//        TacticsDB tacticsDB = new TacticsDB();
////        tacticsDB.createTable("xxxxxx");
//        List<TacticsFlow> tacticsFlows = new ArrayList<>();
//        TacticsFlow tacticsFlow = new TacticsFlow();
//        tacticsFlow.setDnBps(123);
//        tacticsFlow.setUpBps(123);
//        tacticsFlow.setUpMaxBps(12314);
//        tacticsFlow.setTime(231241231);
//        tacticsFlow.setDnMaxBps(124412);
//        tacticsFlow.setPolicyId(123);
//        tacticsFlows.add(tacticsFlow);
//        tacticsDB.addSatics("xxxxxx", tacticsFlows);
        new SelfTask().start();
    }
}
