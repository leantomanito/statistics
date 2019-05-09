/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: JdbcUtil.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月07日, Create
 */
package com.hh.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class JdbcUtil {
    private static DataSource dataSource = null;
    private static Connection conn = null;
    static {
        try{
            Properties properties = new Properties();
            properties.load(JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnect() {
        try {
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static DataSource getDataSource() {
        return dataSource;
    }
}
