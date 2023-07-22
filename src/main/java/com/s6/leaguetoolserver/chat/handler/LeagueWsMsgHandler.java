package com.s6.leaguetoolserver.chat.handler;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.s6.leaguetoolserver.chat.commen.ChatUtils;
import com.s6.leaguetoolserver.chat.config.LeagueServerConfig;
import com.s6.leaguetoolserver.chat.packages.OtherPak;
import com.s6.leaguetoolserver.chat.packages.Package;
import com.s6.leaguetoolserver.chat.packages.enums.MessageType;
import com.s6.leaguetoolserver.chat.packages.enums.OtherPakType;
import com.s6.leaguetoolserver.server.user.service.ILeagueUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class LeagueWsMsgHandler implements IWsMsgHandler {

    @Autowired
    ChatUtils chatUtils;

    @Autowired
    LeagueChatHandler leagueChatHandler;

    @Autowired
    LeagueOtherHandler leagueOtherHandler;

    @Autowired
    LeaguePingHandler leaguePingHandler;

    @Autowired
    ILeagueUserService leagueUserService;

    public ConcurrentMap<MessageType, IWsMsgHandler> handlerMap = new ConcurrentHashMap<>();
    private static Logger log = LoggerFactory.getLogger(LeagueWsMsgHandler.class);

    public LeagueWsMsgHandler(LeagueChatHandler leagueChatHandler, LeagueOtherHandler leagueOtherHandler,LeaguePingHandler leaguePingHandler) {
        handlerMap.put(MessageType.PING, leaguePingHandler);
        handlerMap.put(MessageType.CHAT, leagueChatHandler);
        handlerMap.put(MessageType.OTHER, leagueOtherHandler);
    }
    /**
     * 握手时走这个方法，业务可以在这里获取cookie，request参数等
     */
    @Override
    public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        String clientip = request.getClientIp();
        String region = request.getParam("region");
        String uuid = IdUtil.objectId();
        String uid = request.getParam("uid");
        if(!StringUtils.hasText(uid) || !StringUtils.hasText(region)){
            return httpResponse;
        }
        //如果是游客就使用生成的uuid
        if("tourist".equals(uid)){
            uid = uuid;
        }

        Tio.bindUser(channelContext, uid);
        Tio.bindToken(channelContext, uuid);
        //绑定到群组，后面会有群发
        Tio.bindGroup(channelContext, region);
        log.info("收到来自{}的ws握手包\r\n{}", clientip, request.toString());
        return httpResponse;
    }
    /**
     * @param httpRequest
     * @param httpResponse
     * @param channelContext
     * @throws Exception
     */
    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {

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
    /**
     * 字节消息（binaryType = arraybuffer）过来后会走这个方法
     */
    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        return null;
    }
    /**
     * 当客户端发close flag时，会走这个方法
     */
    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        Tio.remove(channelContext, "receive close flag");
        return null;
    }
    /*
     * 字符消息（binaryType = blob）过来后会走这个方法
     */
    @Override
    public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
        WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
        HttpRequest httpRequest = wsSessionContext.getHandshakeRequest();//获取websocket握手包
        if (log.isDebugEnabled()) {
            log.debug("握手包:{}", httpRequest);
        }
//        log.info("收到ws消息:{}", text);
        Package pak = null;
        try {
            pak = JSON.parseObject(text, Package.class);
        } catch (Exception e) {
            System.out.println(channelContext.userid);
            throw new RuntimeException(e);
        }

        return handlerMap.get(pak.getType()).onText(wsRequest, text,channelContext);
        //channelContext.getToken()
        //String msg = channelContext.getClientNode().toString() + " 说：" + text;
        //用tio-websocket，服务器发送到客户端的Packet都是WsResponse


        //返回值是要发送给客户端的内容，一般都是返回null
    }


    private void sendAccountError(ChannelContext channelContext){
        Package pack = new Package();
        pack.setType(MessageType.OTHER);
        pack.setData(JSON.toJSONString(OtherPak.builder().otherPakType(OtherPakType.ACCOUNT_ERROR).data("账号被冻结无法使用").build()));
        WsResponse wsResponse = WsResponse.fromText(JSON.toJSONString(pack), LeagueServerConfig.CHARSET);
        Tio.sendToId(channelContext.tioConfig,channelContext.getId(), wsResponse);
    }
}