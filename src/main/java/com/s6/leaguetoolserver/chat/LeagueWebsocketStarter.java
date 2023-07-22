package com.s6.leaguetoolserver.chat;

import com.s6.leaguetoolserver.chat.config.LeagueServerConfig;
import com.s6.leaguetoolserver.chat.handler.LeagueWsMsgHandler;
import com.s6.leaguetoolserver.chat.listener.LeagueIpStatListener;
import com.s6.leaguetoolserver.chat.listener.LeagueServerAioListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tio.server.ServerTioConfig;
import org.tio.websocket.server.WsServerStarter;

/**
 * chat服务启动 跟随Bean实例化启动
 */
@Component
public class LeagueWebsocketStarter{
    private final WsServerStarter wsServerStarter;

    @Autowired
    public LeagueWebsocketStarter(LeagueWsMsgHandler leagueWsMsgHandler) throws Exception {
        this(LeagueServerConfig.SERVER_PORT, leagueWsMsgHandler);
    }
    /**
     * 启动WebSocket
     */
    public LeagueWebsocketStarter(int port, LeagueWsMsgHandler wsMsgHandler) throws Exception {
        this.wsServerStarter = new WsServerStarter(port, wsMsgHandler);
        this.setServerTioConfig(wsServerStarter.getServerTioConfig());
        this.doRun();
    }

    private void setServerTioConfig(ServerTioConfig serverTioConfig) {
        //服务名
        serverTioConfig.setName(LeagueServerConfig.PROTOCOL_NAME);
        //Aio监听器
        serverTioConfig.setServerAioListener(LeagueServerAioListener.me);
        //设置ip监控
        serverTioConfig.setIpStatListener(LeagueIpStatListener.me);
        //设置ip统计时间段
        serverTioConfig.ipStats.addDurations(LeagueServerConfig.IpStatDuration.IPSTAT_DURATIONS);
        //设置心跳超时时间
        serverTioConfig.setHeartbeatTimeout(LeagueServerConfig.HEARTBEAT_TIMEOUT);
    }

    private void doRun() throws Exception{
        this.wsServerStarter.start();
    }
}
