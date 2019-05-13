package com.hh;

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

        for (int port = 8000; port < 8011; port++) {
            cachedThreadPool.execute(new TcpServerRunnable(port));
        }
    }

}
