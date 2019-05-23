/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: TacticsFlow.java
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
public class TacticsFlow {

    /**
     * 策略id
     */
    private Integer policyId;
    /**
     * 时间
     */
    private long time;

    /**
     * 上行实际速率
     */
    private long upBps;

    /**
     * 下行实际速率
     */
    private long dnBps;

    /**
     * 上行丢弃速率
     */
    private long upDisBps;

    /**
     * 下行丢弃速率
     */
    private long dnDisBps;

    /**
     * 上行峰值速率
     */
    private long upMaxBps;

    /**
     * 下行峰值速率
     */
    private long dnMaxBps;

    /**
     * 上行最小速率
     */
    private long upMinBps;

    /**
     * 下行最小速率
     */
    private long dnMinBps;

    /**
     * 上行直通流量
     */
    private long upWhiteBps;

    /**
     * 下行直通流量
     */
    private long dnWhiteBps;
    public TacticsFlow(){}

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getUpBps() {
        return upBps;
    }

    public void setUpBps(long upBps) {
        this.upBps = upBps;
    }

    public long getDnBps() {
        return dnBps;
    }

    public void setDnBps(long dnBps) {
        this.dnBps = dnBps;
    }

    public long getUpMaxBps() {
        return upMaxBps;
    }

    public void setUpMaxBps(long upMaxBps) {
        this.upMaxBps = upMaxBps;
    }

    public long getDnMaxBps() {
        return dnMaxBps;
    }

    public void setDnMaxBps(long dnMaxBps) {
        this.dnMaxBps = dnMaxBps;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public long getUpDisBps() {
        return upDisBps;
    }

    public void setUpDisBps(long upDisBps) {
        this.upDisBps = upDisBps;
    }

    public long getDnDisBps() {
        return dnDisBps;
    }

    public void setDnDisBps(long dnDisBps) {
        this.dnDisBps = dnDisBps;
    }

    public long getUpMinBps() {
        return upMinBps;
    }

    public void setUpMinBps(long upMinBps) {
        this.upMinBps = upMinBps;
    }

    public long getDnMinBps() {
        return dnMinBps;
    }

    public void setDnMinBps(long dnMinBps) {
        this.dnMinBps = dnMinBps;
    }

    public long getUpWhiteBps() {
        return upWhiteBps;
    }

    public void setUpWhiteBps(long upWhiteBps) {
        this.upWhiteBps = upWhiteBps;
    }

    public long getDnWhiteBps() {
        return dnWhiteBps;
    }

    public void setDnWhiteBps(long dnWhiteBps) {
        this.dnWhiteBps = dnWhiteBps;
    }

}
