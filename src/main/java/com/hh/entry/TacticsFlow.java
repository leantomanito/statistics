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
     * 探针id
     */
    private Integer policyId;

    /**
     * 时间
     */
    private long time;

    /**
     * 时间粒度
     */
    private int timeGranu;

    /**
     * 上行实际速率
     */
    private long upBps;

    /**
     * 下行实际速率
     */
    private long dnBps;


    /**
     * 双向实际速率
     */
    private long totalBps;

    /**
     * 上行丢弃流量
     */
    private long upDisByte;

    /**
     * 上行丢弃速率
     */
    private long upDisBps;

    /**
     * 下行丢弃流量
     */
    private long dnDisByte;

    /**
     * 下行丢弃速率
     */
    private long dnDisBps;

    /**
     * 双向丢弃流量
     */
    private long totalDisByte;

    /**
     * 双向丢弃速率
     */
    private long totalDisBps;

    /**
     * 上行峰值速率
     */
    private long upMaxBps;

    /**
     * 下行峰值速率
     */
    private long dnMaxBps;

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTimeGranu() {
        return timeGranu;
    }

    public void setTimeGranu(int timeGranu) {
        this.timeGranu = timeGranu;
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

    @Override
    public String toString() {
        return "TacticsFlow{" +
                "policyId=" + policyId +
                ", time=" + time +
                ", timeGranu=" + timeGranu +
                ", upBps=" + upBps +
                ", dnBps=" + dnBps +
                ", totalBps=" + totalBps +
                ", upDisByte=" + upDisByte +
                ", upDisBps=" + upDisBps +
                ", dnDisByte=" + dnDisByte +
                ", dnDisBps=" + dnDisBps +
                ", totalDisByte=" + totalDisByte +
                ", totalDisBps=" + totalDisBps +
                ", upMaxBps=" + upMaxBps +
                ", dnMaxBps=" + dnMaxBps +
                '}';
    }
}
