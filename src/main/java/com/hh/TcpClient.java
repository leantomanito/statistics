package com.hh;

import com.hh.db.TacticsDB;
import com.hh.jdbc.JdbcUtil;
import com.hh.thread.TcpServerRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟被监听服务
 * @author zyj
 * @date 16:07 2019/5/12
 */
public class TcpClient {
    
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        for (int port = 8000; port < 8001; port++) {
            cachedThreadPool.execute(new TcpServerRunnable(port));
        }

        TacticsDB tacticsDB = new TacticsDB();
    }

}
