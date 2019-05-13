/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: TcpRunnable.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月09日, Create
 */
package com.hh.thread;

import com.hh.entry.TacticsTCP;
import com.hh.entry.server.TcpServer;
import com.hh.tcp.TcpClient;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TcpServerRunnable implements Runnable {
    private int port;

    public TcpServerRunnable(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        new TcpServer(port).init();
    }
}
