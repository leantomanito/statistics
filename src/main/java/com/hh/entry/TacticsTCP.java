/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: TacticsTCP.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月09日, Create
 */
package com.hh.entry;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TacticsTCP {
    private String ip;
    private Integer port;
    private String commver;

    public TacticsTCP(String ip, Integer port, String commver) {
        this.ip = ip;
        this.port = port;
        this.commver = commver;
    }

    public String feelOutMsg(){
        return "hello reader comm" + this.commver;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getCommver() {
        return commver;
    }

    public void setCommver(String commver) {
        this.commver = commver;
    }

    @Override
    public String toString() {
        return "TacticsTCP{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", commver='" + commver + '\'' +
                '}';
    }
}
