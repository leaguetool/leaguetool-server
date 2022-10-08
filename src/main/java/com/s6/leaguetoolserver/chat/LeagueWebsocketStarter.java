package com.s6.leaguetoolserver.chat;

import com.s6.leaguetoolserver.LeaguetoolServerApplication;
import com.s6.leaguetoolserver.chat.config.LeagueServerConfig;
import com.s6.leaguetoolserver.chat.handler.LeagueWsMsgHandler;
import com.s6.leaguetoolserver.chat.listener.LeagueIpStatListener;
import com.s6.leaguetoolserver.chat.listener.LeagueServerAioListener;
import org.tio.server.ServerTioConfig;
import org.tio.websocket.server.WsServerStarter;

public class LeagueWebsocketStarter {
    private WsServerStarter wsServerStarter;
    private ServerTioConfig serverTioConfig;
    /**
     *
     * @author tanyaowu
     */
    public LeagueWebsocketStarter(int port, LeagueWsMsgHandler wsMsgHandler) throws Exception {
        wsServerStarter = new WsServerStarter(port, wsMsgHandler);
        serverTioConfig = wsServerStarter.getServerTioConfig();
        serverTioConfig.setName(LeagueServerConfig.PROTOCOL_NAME);
        serverTioConfig.setServerAioListener(LeagueServerAioListener.me);
        //设置ip监控
        serverTioConfig.setIpStatListener(LeagueIpStatListener.me);
        //设置ip统计时间段
        serverTioConfig.ipStats.addDurations(LeagueServerConfig.IpStatDuration.IPSTAT_DURATIONS);
        //设置心跳超时时间
        serverTioConfig.setHeartbeatTimeout(LeagueServerConfig.HEARTBEAT_TIMEOUT);
//        if (P.getInt("ws.use.ssl", 1) == 1) {
//            //如果你希望通过wss来访问，就加上下面的代码吧，不过首先你得有SSL证书（证书必须和域名相匹配，否则可能访问不了ssl）
////            String keyStoreFile = "classpath:config/ssl/keystore.jks";
////            String trustStoreFile = "classpath:config/ssl/keystore.jks";
////            String keyStorePwd = "214323428310224";
//            String keyStoreFile = P.get("ssl.keystore", null);
//            String trustStoreFile = P.get("ssl.truststore", null);
//            String keyStorePwd = P.get("ssl.pwd", null);
//            serverTioConfig.useSsl(keyStoreFile, trustStoreFile, keyStorePwd);
//        }
    }
    /**
     * @author tanyaowu
     */
    public static void start() throws Exception {
        LeagueWebsocketStarter appStarter = new LeagueWebsocketStarter(LeagueServerConfig.SERVER_PORT, LeagueWsMsgHandler.me);
        appStarter.wsServerStarter.start();
    }
    /**
     * @return the serverTioConfig
     */
    public ServerTioConfig getServerTioConfig() {
        return serverTioConfig;
    }
    public WsServerStarter getWsServerStarter() {
        return wsServerStarter;
    }
    public static void main(String[] args)  {
        try {
            //启动websocket server
            start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
