/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: Board.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月09日, Create
 */
package com.hh.entry;

import java.util.LinkedList;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class Board {
    private Integer id;
    private Integer probeId;
    private Integer domainId;
    private Integer linkId;
    private String ip;
    private Integer port;
    private String commver;
    private LinkedList<TacticsFlow> tacticsFlows = new LinkedList<>();

    public Board(Integer id, Integer probeId, Integer domainId, Integer linkId, String ip, Integer port, String commver) {
        this.id = id;
        this.probeId = probeId;
        this.domainId = domainId;
        this.linkId = linkId;
        this.ip = ip;
        this.port = port;
        this.commver = commver;
    }

    public Integer getProbeId() {
        return probeId;
    }

    public void setProbeId(Integer probeId) {
        this.probeId = probeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
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

    public LinkedList<TacticsFlow> getTacticsFlows() {
        return tacticsFlows;
    }

    public void setTacticsFlows(LinkedList<TacticsFlow> tacticsFlows) {
        this.tacticsFlows = tacticsFlows;
    }
    public String feelOutMsg(){
        return "hello reader comm" + this.commver;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", probeId=" + probeId +
                ", domainId=" + domainId +
                ", linkId=" + linkId +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", commver='" + commver + '\'' +
                ", tacticsFlows=" + tacticsFlows +
                '}';
    }
}
