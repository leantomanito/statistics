/*
 * Copyright (c) 2008-2016 浩瀚深度 All Rights Reserved.
 *
 * FileName: TacticsChannel.java
 *
 * Description：
 *
 * History:
 * v1.0.0, zyj, 2019年05月09日, Create
 */
package com.hh.entry;

import io.netty.channel.Channel;

/**
 * @author zyj
 * @version 1.0.0
 * @since 1.0.0
 */
public class TacticsChannel {
    private Channel channel;

    private TacticsTCP tacticsTCP;

    public TacticsChannel(Channel channel, TacticsTCP tacticsTCP) {
        this.channel = channel;
        this.tacticsTCP = tacticsTCP;
    }

    public TacticsTCP getTacticsTCP() {
        return tacticsTCP;
    }

    public void setTacticsTCP(TacticsTCP tacticsTCP) {
        this.tacticsTCP = tacticsTCP;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
