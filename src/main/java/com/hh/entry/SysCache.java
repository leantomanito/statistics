/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: SysCache.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月09日, Create
 */
package com.hh.entry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class SysCache {

    //Tcp链接对象
   public static Map<String, TacticsChannel> channelMap = new HashMap<>();
    //Tcp链接对象
    public static Map<String, TacticsChannel> sucChannelMap = new HashMap<>();
}
