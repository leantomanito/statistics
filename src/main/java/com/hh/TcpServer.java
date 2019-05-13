package com.hh;

import com.hh.db.TacticsDB;
import com.hh.entry.SysCache;
import com.hh.entry.TacticsTCP;
import com.hh.jdbc.JdbcUtil;
import com.hh.thread.TcpRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * tcp服务
 *
 * @author zyj
 * @date 16:05 2019/5/12
 */
public class TcpServer {

    private static Logger log = LoggerFactory.getLogger(TcpServer.class);
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        //查询 客户端记录 jdbc

        //启动监听 代码

        //循环

        //读取 客户端 信息

    }


}
