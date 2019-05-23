/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: TacticsDB.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月09日, Create
 */
package com.hh.db;

import com.hh.entry.Board;
import com.hh.entry.EntryFlow;
import com.hh.entry.TacticsFlow;
import com.hh.jdbc.JdbcUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TacticsDB extends JdbcTemplate {

    public TacticsDB() {
        setDataSource(JdbcUtil.getDataSource());
    }

    public Map<Integer, Board> getLinkMsg() {
        String sql = "SELECT b.id,b.probeid,b.domainid,b.ipaddr,b.tcpport,bi.commver FROM tm_board as b left join tm_board_info as bi on b.id = bi.boardid";
        Map<Integer, Board> boardMap = new HashMap<>();
        SqlRowSet sqlRowSet = queryForRowSet(sql);
        while (sqlRowSet.next()) {
            boardMap.put(sqlRowSet.getInt("id"),new Board(sqlRowSet.getInt("id"),
                    sqlRowSet.getInt("probeid"),
                    sqlRowSet.getInt("domainid"),
                    -1,
                    sqlRowSet.getString("ipaddr"),
                    sqlRowSet.getInt("tcpport"),
                    sqlRowSet.getString("commver")));
        }
        return boardMap;
    }

    public boolean isExistTable(String tableName) {
        String sql = "select tablename from tables_views where tablename = ?";
        List<String> tables = queryForList(sql, String.class, tableName);
        return !tables.isEmpty();
    }

    public void createTable(String tableName) {
        String sql = "select f_ctb_ctrl_mtuple('" + tableName + "')";
        execute(sql);
    }

    public void addSatics(String table, List<EntryFlow> entryFlows) {
        String sql = "insert into " + table + "(time, probeid, boardid,  linkid, domainid, policyid, upbps, dnbps, upmaxbps, dnmaxbps," +
                " updisbps, dndisbps, upminbps, dnminbps, upwhitebps, dnwhitebps) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        List<Object[]> objects = new ArrayList<>();
        for (EntryFlow flow : entryFlows) {
            Map<Integer, TacticsFlow> tacticsFlowMap = flow.getTacticsFlowMap();
            Board board = flow.getBoard();
            for (Integer key : tacticsFlowMap.keySet()){
                TacticsFlow tacticsFlow = tacticsFlowMap.get(key);
                Object[] obj = new Object[]{tacticsFlow.getTime(), board.getProbeId(), board.getId(), board.getLinkId(), board.getDomainId(), tacticsFlow.getPolicyId(),
                        tacticsFlow.getUpBps(), tacticsFlow.getDnBps(), tacticsFlow.getUpMaxBps(),
                        tacticsFlow.getDnMaxBps(), tacticsFlow.getUpDisBps(), tacticsFlow.getDnDisBps(),
                        tacticsFlow.getUpMinBps(), tacticsFlow.getDnMinBps(),tacticsFlow.getUpWhiteBps(),
                        tacticsFlow.getDnWhiteBps()};
                objects.add(obj);
            }
        }
        batchUpdate(sql, objects);
    }
}
