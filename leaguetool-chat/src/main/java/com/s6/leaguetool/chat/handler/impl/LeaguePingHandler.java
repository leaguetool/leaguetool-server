package com.s6.leaguetool.chat.handler.impl;

import com.s6.leaguetool.chat.config.LeagueServerConfig;
import com.s6.leaguetool.chat.handler.AbstractHandler;
import com.s6.leaguetool.chat.packages.Ping;
import com.s6.leaguetool.chat.packages.enums.HandlerType;
import com.s6.leaguetool.chat.packages.enums.MessageType;
import com.s6.leaguetool.chat.packages.Package;
import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;

/**
 * @author cailong
 * PING PONG 只是用于心跳检测
 * PING 客户端发送给服务器
 * PONG 服务器发送给客户端
 * @see <a href="https://tools.ietf.org/html/rfc6455#section-5.5.2">PING PONG</a>
 */
@Component
public class LeaguePingHandler extends AbstractHandler<Ping> {

    /**
     * 获取处理器类型 {@link HandlerType}
     * @return 处理器类型 {@link HandlerType}
     * @see HandlerType#PING
     * @see MessageType#PONG
     */
    @Override
    public Pair<HandlerType, MessageType> getHandlerType() {
        return Pair.of(HandlerType.PING, MessageType.PING);
    }

    /**
     * 处理消息
     * @param wsRequest 请求 {@link WsRequest}
     * @param data 消息 {@link Ping} 一般是没有数据的
     * @param channelContext 通道上下文 {@link ChannelContext}
     */
    @Override
    public void onMessage(WsRequest wsRequest, Ping data, ChannelContext channelContext) {
        WsResponse wsResponse = WsResponse.fromText(JSON.toJSONString(Package.of(MessageType.PONG)), LeagueServerConfig.CHARSET);
        //发送给用户的id
        Tio.sendToToken(channelContext.tioConfig, channelContext.getToken(), wsResponse);
    }
}
