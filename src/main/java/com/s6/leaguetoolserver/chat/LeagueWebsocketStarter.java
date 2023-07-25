package com.s6.leaguetoolserver.chat;

import com.s6.leaguetoolserver.chat.config.LeagueServerConfig;
import com.s6.leaguetoolserver.chat.handler.LeagueWsMsgHandler;
import com.s6.leaguetoolserver.chat.listener.LeagueIpStatListener;
import com.s6.leaguetoolserver.chat.listener.LeagueServerAioListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tio.server.ServerTioConfig;
import org.tio.websocket.server.WsServerStarter;

import java.io.IOException;

/**
 * chat服务启动 跟随Bean实例化启动
 */
@Configuration
public class LeagueWebsocketStarter{
    @Bean
    public WsServerStarter wsServerStarter(LeagueWsMsgHandler leagueWsMsgHandler) throws IOException {
        // 设置处理器
        WsServerStarter wsServerStarter = new WsServerStarter(LeagueServerConfig.SERVER_PORT, leagueWsMsgHandler);
        // 获取到ServerTioConfig
        ServerTioConfig serverTioConfig = wsServerStarter.getServerTioConfig();
        this.setServerTioConfig(serverTioConfig);
        // 启动
        wsServerStarter.start();
        return wsServerStarter;
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
}
