package com.s6.leaguetool.chat;

import com.s6.leaguetool.chat.config.LeagueServerConfig;
import com.s6.leaguetool.chat.handler.impl.LeagueDefaultHandler;
import com.s6.leaguetool.chat.listener.LeagueIpStatListener;
import com.s6.leaguetool.chat.listener.LeagueServerAioListener;
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
    /**
     * 启动服务
     * @param leagueDefaultHandler 默认处理器
     * @return {@link WsServerStarter} 服务启动器
     * @throws IOException IO异常
     */
    @Bean
    public WsServerStarter wsServerStarter(LeagueDefaultHandler leagueDefaultHandler) throws IOException {
        // 设置处理器
        WsServerStarter wsServerStarter = new WsServerStarter(LeagueServerConfig.SERVER_PORT, leagueDefaultHandler);
        // 获取到ServerTioConfig
        ServerTioConfig serverTioConfig = wsServerStarter.getServerTioConfig();
        this.setServerTioConfig(serverTioConfig);
        // 启动
        wsServerStarter.start();
        return wsServerStarter;
    }

    /**
     * 设置服务配置
     * @param serverTioConfig 服务配置
     */
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
