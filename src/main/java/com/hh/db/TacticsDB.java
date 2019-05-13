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

import com.hh.entry.TacticsFlow;
import com.hh.entry.TacticsTCP;
import com.hh.jdbc.JdbcUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TacticsDB extends JdbcTemplate {

    public TacticsDB(){
        setDataSource(JdbcUtil.getDataSource());
    }

    public List<TacticsTCP> getLinkMsg() {
        String sql = "SELECT b.ipaddr,b.tcpport,p.commver FROM tm_board as b left join tm_probe_info as p on b.probeid = p.probeid";
        List<TacticsTCP> tacticsTCPS = new ArrayList<>();
        SqlRowSet sqlRowSet = queryForRowSet(sql);
        while (sqlRowSet.next()) {
            tacticsTCPS.add(new TacticsTCP(sqlRowSet.getString("ipaddr"),
                    sqlRowSet.getInt("tcpport"),
                    sqlRowSet.getString("commver")));
        }
        return tacticsTCPS;
    }

    public boolean isExistTable(String tableName) {
        String sql = "select tablename from tables_views where tablename = ?";
        List<String> tables = queryForList(sql, String.class, tableName);
        return !tables.isEmpty();
    }

    public void createTable(String tableName){
        String sql ="select f_ctb_service('"+tableName+"')";
        execute(sql);
    }

    public void addSatics(String table, List<TacticsFlow> tacticsFlows) {
        String sql = "insert into " + table + "(time, probeid, upbps, dnbps, upmaxbps, dnmaxbps) " +
                "values (?, ?, ?, ?, ?, ?)";
        List<Object[]> objects = new ArrayList<>();
        for (TacticsFlow flow : tacticsFlows) {
            Object[] obj = new Object[]{flow.getTime(), flow.getPolicyId(), flow.getUpBps(), flow.getDnBps(), flow.getUpMaxBps(), flow.getDnMaxBps()};
            objects.add(obj);
        }
        batchUpdate(sql, objects);
    }
}
