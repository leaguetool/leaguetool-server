package com.s6.leaguetool.chat.handler.impl;

import com.s6.leaguetool.chat.commen.ChatUtils;
import com.s6.leaguetool.chat.handler.AbstractHandler;
import com.s6.leaguetool.chat.packages.enums.HandlerType;
import com.s6.leaguetool.chat.packages.enums.MessageType;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;

/**
 * @author cailong
 * 默认处理器, 用于处理握手包, 用于处理握手后, 用于分发消息处理器
 * 消息分发器用来分发消息给其他handler处理, 默认处理器只处理握手包
 * 握手包 {@link LeagueDefaultHandler#handshake(HttpRequest, HttpResponse, ChannelContext)}
 * 握手后 {@link LeagueDefaultHandler#onAfterHandshaked(HttpRequest, HttpResponse, ChannelContext)}
 * 分发消息处理器 {@link LeagueDefaultHandler#onMessage(WsRequest, String, ChannelContext)}
 */
@Component
@RequiredArgsConstructor
public class LeagueDefaultHandler extends AbstractHandler {

    private final ChatUtils chatUtils;

    //创建日志工厂
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LeagueDefaultHandler.class);

    /**
     * 获取处理器类型 {@link HandlerType}
     * @see HandlerType#DEFAULT
     * @return 处理器类型 {@link Pair}
     */
    @Override
    public Pair<HandlerType, MessageType> getHandlerType() {
        return Pair.of(HandlerType.DEFAULT, null);
    }

    /**
     * 这里不需要处理消息, 因为默认处理器只处理握手包
     * @param wsRequest 请求 {@link WsRequest}
     * @param text 消息 {@link String}
     * @param channelContext 通道上下文 {@link ChannelContext}
     */
    @Override
    public void onMessage(WsRequest wsRequest, String text, ChannelContext channelContext) {}

    /**
     * 握手包
     * @param request 请求
     * @param httpResponse 响应
     * @param channelContext 通道上下文
     * @return 响应
     */
    @Override
    public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext) {
        String clientip = request.getClientIp();
        String region = request.getParam("region");
        String uuid = IdUtil.objectId();
        String uid = request.getParam("uid");
        //如果uid或者region为空, 就不握手进行链接
        if(!StringUtils.hasText(uid) || !StringUtils.hasText(region)){
            return null;
        }
        //如果是游客就使用生成的uuid
        if("tourist".equals(uid)){
            uid = uuid;
        }

        Tio.bindUser(channelContext, uid);
        Tio.bindToken(channelContext, uuid);
        //绑定到群组，后面会有群发
        Tio.bindGroup(channelContext, region);
        log.info("收到来自{}的ws握手包\r\n{}", clientip, request);
        return httpResponse;
    }

    /**
     * 握手后
     * @param httpRequest 请求
     * @param httpResponse  响应
     * @param channelContext 通道上下文
     */
    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) {

        String region = httpRequest.getParam("region");
        //发送token
        chatUtils.sendToken(channelContext);
        //服务器的总连接的用户
//      int count = Tio.getAllChannelContexts(channelContext.tioConfig).getObj().size();
        //获取当前大区有多少人在开黑大厅
        chatUtils.sendInitHot(channelContext, region);
        //发送基础信息
        chatUtils.initBaseInfo(channelContext);
        //发送聊天记录
        chatUtils.sendChatHistory(channelContext, region);
    }
}
