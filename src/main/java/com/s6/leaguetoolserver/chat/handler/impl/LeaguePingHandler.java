package com.s6.leaguetoolserver.chat.handler.impl;

import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSON;
import com.s6.leaguetoolserver.chat.config.LeagueServerConfig;
import com.s6.leaguetoolserver.chat.handler.AbstractHandler;
import com.s6.leaguetoolserver.chat.packages.Package;
import com.s6.leaguetoolserver.chat.packages.enums.HandlerType;
import com.s6.leaguetoolserver.chat.packages.enums.MessageType;
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
public class LeaguePingHandler extends AbstractHandler {
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
     * @param text 消息 {@link String}
     * @param channelContext 通道上下文 {@link ChannelContext}
     */
    @Override
    public void onMessage(WsRequest wsRequest, String text, ChannelContext channelContext) {
        WsResponse wsResponse = WsResponse.fromText(JSON.toJSONString(Package.of(MessageType.PONG)), LeagueServerConfig.CHARSET);
        //发送给用户的id
        Tio.sendToToken(channelContext.tioConfig, channelContext.getToken(), wsResponse);
    }
}
