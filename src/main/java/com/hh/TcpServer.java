package com.hh;

import com.hh.entry.TacticsTCP;
import com.hh.thread.TcpRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * tcp服务
 * @author zyj
 * @date 16:05 2019/5/12
 */
public class TcpServer {


    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        //查询 客户端记录 jdbc

        //启动监听 代码

        //循环

        //读取 客户端 信息

//        while (true){
//
//            if () 如果缓存中没有 说明  已经成功接收到返回数据  在倒计时到的时候开始下一次接收 俩个条件
//            {
//              cachedThreadPool.execute(new TcpRunnable(tacticsTCP));
//            }
//
//        }

        List<TacticsTCP> tacticsTCPS = new ArrayList<>();
        tacticsTCPS.add(new TacticsTCP("127.0.0.1", 8001, "4.31"));
        tacticsTCPS.add(new TacticsTCP("127.0.0.1", 8002, "4.31"));
        tacticsTCPS.add(new TacticsTCP("127.0.0.1", 8003, "4.31"));
        tacticsTCPS.add(new TacticsTCP("127.0.0.1", 8004, "4.31"));
        tacticsTCPS.add(new TacticsTCP("127.0.0.1", 8005, "4.31"));

        for (TacticsTCP tacticsTCP : tacticsTCPS) {
            cachedThreadPool.execute(new TcpRunnable(tacticsTCP));
            List<Runnable> runnables = cachedThreadPool.shutdownNow();
            runnables.
        }

    }


}
