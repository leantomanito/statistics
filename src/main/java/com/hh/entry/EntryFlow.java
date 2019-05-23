/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: EntryFlow.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月15日, Create
 */
package com.hh.entry;

import java.util.Map;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class EntryFlow {
    private Board board;
    private Map<Integer,TacticsFlow> tacticsFlowMap;

    public EntryFlow(Board board, Map<Integer, TacticsFlow> tacticsFlowMap) {
        this.board = board;
        this.tacticsFlowMap = tacticsFlowMap;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Map<Integer, TacticsFlow> getTacticsFlowMap() {
        return tacticsFlowMap;
    }

    public void setTacticsFlowMap(Map<Integer, TacticsFlow> tacticsFlowMap) {
        this.tacticsFlowMap = tacticsFlowMap;
    }
}
