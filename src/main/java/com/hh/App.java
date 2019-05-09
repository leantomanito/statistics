package com.hh;

import com.hh.Task.SelfTask;
import com.hh.db.TacticsDB;
import com.hh.entry.TacticsTCP;
import com.hh.jdbc.JdbcUtil;
import com.hh.thread.TcpRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App
{
    private static final Logger log = LoggerFactory.getLogger(App.class);
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main( String[] args )
    {/*
        List<TacticsTCP> tacticsTCPS = new ArrayList<>();
        tacticsTCPS.add(new TacticsTCP("127.0.0.1", 8778, "123"));
        tacticsTCPS.add(new TacticsTCP("127.0.0.1", 8779, "123"));
        for (TacticsTCP tacticsTCP : tacticsTCPS) {
            cachedThreadPool.execute(new TcpRunnable(tacticsTCP));
        }
        new SelfTask().start();*/
        TacticsDB tacticsDB = new TacticsDB(JdbcUtil.getDataSource());
log.info(tacticsDB.getLinkMsg().toString());
        log.info("是否存在："+tacticsDB.isExistTable("td_service_1d11_m_201905"));
    }
}
