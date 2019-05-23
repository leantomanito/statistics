/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: CommData.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月09日, Create
 */
package com.hh.entry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class CommData {

    //Tcp链接对象
    public static Map<Integer, Board> boardFlowMap = new ConcurrentHashMap<>();

    public static final Lock LOCK = new ReentrantLock();
    public static final Condition CONDITION = LOCK.newCondition();

}
