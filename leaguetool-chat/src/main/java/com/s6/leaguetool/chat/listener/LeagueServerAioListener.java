package com.s6.leaguetool.chat.listener;

import com.s6.leaguetool.chat.commen.HotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.intf.Packet;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.WsServerAioListener;

import java.util.Set;

public class LeagueServerAioListener extends WsServerAioListener {
    private static Logger log = LoggerFactory.getLogger(LeagueServerAioListener.class);
    public static final LeagueServerAioListener me = new LeagueServerAioListener();
    private LeagueServerAioListener() {
    }
    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect) throws Exception {
        super.onAfterConnected(channelContext, isConnected, isReconnect);
        if (log.isInfoEnabled()) {
//            log.info("onAfterConnected\r\n{}", channelContext);
        }
    }
    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) throws Exception {
        super.onAfterSent(channelContext, packet, isSentSuccess);
        if (log.isInfoEnabled()) {
//            log.info("onAfterSent\r\n{}\r\n{}", packet.logstr(), channelContext);
        }
    }
    @Override
    public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) throws Exception {
        super.onBeforeClose(channelContext, throwable, remark, isRemove);
        if (log.isInfoEnabled()) {
//            log.info("onBeforeClose\r\n{}", channelContext);
        }
        WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
        if (wsSessionContext != null && wsSessionContext.isHandshaked()) {
            Set<String> obj = channelContext.getGroups().getObj();
            if(!obj.stream().findFirst().isPresent()){
                return;
            }
            String area = obj.stream().findFirst().get();
            //获取加入的群有还有多少个人
            int i = Tio.groupCount(channelContext.tioConfig, area);
            //服务器总共还有多少人
            int count = Tio.getAllChannelContexts(channelContext.tioConfig).getObj().size();
            log.info("onBeforeClose: 当前用户id：{},大区【{}】剩余{}人,服务器总共还有{}人", channelContext.userid, area, i, count);
            //群发给所在大区的所有人改变热度
            int hot = HotUtils.getHot(count);

//            String msg = channelContext.getClientNode().toString() + " 离开了，现在共有【" + count + "】人在线";
            //用tio-websocket，服务器发送到客户端的Packet都是WsResponse
//            WsResponse wsResponse = WsResponse.fromText(msg, LeagueServerConfig.CHARSET);
            //群发
//            Tio.sendToGroup(channelContext.tioConfig, Const.GROUP_ID, wsResponse);
        }

    }
    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize) throws Exception {
        super.onAfterDecoded(channelContext, packet, packetSize);
        if (log.isInfoEnabled()) {
//            log.info("onAfterDecoded\r\n{}\r\n{}", packet.logstr(), channelContext);
        }
    }
    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes) throws Exception {
        super.onAfterReceivedBytes(channelContext, receivedBytes);
        if (log.isInfoEnabled()) {
//            log.info("onAfterReceivedBytes\r\n{}", channelContext);
        }
    }
    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, long cost) throws Exception {
        super.onAfterHandled(channelContext, packet, cost);
        if (log.isInfoEnabled()) {
//            log.info("onAfterHandled\r\n{}\r\n{}", packet.logstr(), channelContext);
        }

    }

    @Override
    public boolean onHeartbeatTimeout(ChannelContext channelContext, Long aLong, int i) {
        return false;
    }
}