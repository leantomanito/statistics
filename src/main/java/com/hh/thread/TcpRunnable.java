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
import com.hh.tcp.TcpClient;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TcpRunnable implements Runnable {
    private TacticsTCP tacticsTCP;

    public TcpRunnable(TacticsTCP tacticsTCP) {
        this.tacticsTCP = tacticsTCP;
    }

    @Override
    public void run() {
        new TcpClient(tacticsTCP).init();
    }
}
