package com.s6.leaguetoolserver.chat.handler;

import cn.hutool.core.lang.Pair;
import com.s6.leaguetoolserver.chat.packages.enums.HandlerType;
import com.s6.leaguetoolserver.chat.packages.enums.MessageType;
import org.tio.core.ChannelContext;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.server.handler.IWsMsgHandler;

/**
 * @author cailong
 * 处理器接口
 * DEFAULT 默认处理器
 */
public interface LeagueHandler extends IWsMsgHandler {
    /**
     * 获取处理器类型 {@link HandlerType}
     * @return 处理器类型 {@link HandlerType}
     */
    Pair<HandlerType, MessageType> getHandlerType();

    /**
     * 处理消息
     * @param wsRequest 请求 {@link WsRequest}
     * @param text 消息 {@link String}
     * @param channelContext 通道上下文 {@link ChannelContext}
     */
    void onMessage(WsRequest wsRequest, String text, ChannelContext channelContext);

}
