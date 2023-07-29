package com.s6.leaguetool.chat.handler;

import com.s6.leaguetool.chat.LeagueWebsocketStarter;
import com.s6.leaguetool.chat.packages.enums.HandlerType;
import com.s6.leaguetool.chat.packages.enums.MessageType;
import com.s6.leaguetool.chat.packages.Package;
import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.tio.core.ChannelContext;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsSessionContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author cailong
 * 抽象处理器 {@link LeagueHandler}
 * 所有处理器都需要继承这个类, 并且实现 {@link LeagueHandler} 接口
 * 它作用是用于分发消息处理器
 * @see LeagueHandler
 * @see AbstractHandler
 */
public abstract class AbstractHandler implements LeagueHandler, ApplicationContextAware {

    protected final ConcurrentMap<MessageType, LeagueHandler> leagueHandlerConcurrentMap = new ConcurrentHashMap<>();

    private ApplicationContext applicationContext;

    private static final Logger log = LoggerFactory.getLogger(AbstractHandler.class);

    /**
     * 抽象方法, 所有处理器都需要实现这个方法
     * @param wsRequest 请求 {@link WsRequest}
     * @param text 消息 {@link String}
     * @param channelContext 通道上下文 {@link ChannelContext}
     */
    public abstract void onMessage(WsRequest wsRequest, String text, ChannelContext channelContext);

    /**
     * 字节消息 {@link WsRequest}
     * @param wsRequest 请求 {@link WsRequest}
     * @param bytes 字节消息 {@link byte[]}
     * @param channelContext 通道上下文 {@link ChannelContext}
     * @return {@link Object}
     */
    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) {
        return null;
    }

    /**
     * 关闭消息
     * @param wsRequest 请求 {@link WsRequest}
     * @param bytes 字节消息 {@link byte[]}
     * @param channelContext 通道上下文 {@link ChannelContext}
     * @return {@link Object}
     */
    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) {
        return null;
    }

    /**
     * 握手消息
     * @param httpRequest 请求 {@link HttpRequest}
     * @param httpResponse 响应 {@link HttpResponse}
     * @param channelContext 通道上下文 {@link ChannelContext}
     * @return {@link HttpResponse}
     */
    @Override
    public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) {
        return null;
    }

    /**
     * 握手后
     * @param httpRequest 请求 {@link HttpRequest}
     * @param httpResponse 响应 {@link HttpResponse}
     * @param channelContext 通道上下文 {@link ChannelContext}
     */
    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) {}

    /**
     * 该方法用于处理文本消息, 是所有处理器的入口
     * 程序入口在 {@link LeagueWebsocketStarter} 使用了
     * @param wsRequest 请求
     * @param text 消息
     * @param channelContext 通道上下文
     * @return {@link Object}
     */
    @Override
    public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) {
        WsSessionContext wsSessionContext = (WsSessionContext) channelContext.get();
        //获取websocket握手包
        HttpRequest httpRequest = wsSessionContext.getHandshakeRequest();
        if (log.isDebugEnabled()) {
            log.debug("握手包:{}", httpRequest);
        }
        Package pak;
        try {
            pak = JSON.parseObject(text, Package.class);
        } catch (Exception e) {
            log.error("解析网络包异常,userId: {}, package: {}", channelContext.userid, text);
            throw new RuntimeException(e);
        }
        MessageType type = pak.getType();
        LeagueHandler leagueHandler = getMessageHandler(type);
        if(null == leagueHandler){
            log.warn("消息类型: {}, 没有找到合适的消息处理器", type.name());
            return null;
        }
        leagueHandler.onMessage(wsRequest, text, channelContext);
        return null;
    }

    /**
     * 获取消息处理器
     * @param type 消息类型 {@link MessageType}
     * @return {@link LeagueHandler} 消息处理器
     */
    private LeagueHandler getMessageHandler(MessageType type) {
        //如果没有初始化，那么就初始化
        if (leagueHandlerConcurrentMap.isEmpty()) {
            Map<String, LeagueHandler> beansOfType = this.applicationContext.getBeansOfType(LeagueHandler.class);
            beansOfType.forEach((key, value) -> {
                LeagueHandler leagueHandler = beansOfType.get(key);
                Pair<HandlerType, MessageType> handlerType = leagueHandler.getHandlerType();
                if(null != handlerType && null != handlerType.getValue()){
                    leagueHandlerConcurrentMap.put(handlerType.getValue(), leagueHandler);
                }
            });
        }
        return leagueHandlerConcurrentMap.get(type);
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
