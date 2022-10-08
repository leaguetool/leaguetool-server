package com.s6.leaguetoolserver.chat.handler;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.s6.leaguetoolserver.chat.config.LeagueServerConfig;
import com.s6.leaguetoolserver.chat.packages.OtherPak;
import com.s6.leaguetoolserver.chat.packages.Package;
import com.s6.leaguetoolserver.chat.packages.enums.MessageType;
import com.s6.leaguetoolserver.chat.packages.enums.OtherPakType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.HashMap;
import java.util.Map;

public class LeagueWsMsgHandler implements IWsMsgHandler {
    public static Map<MessageType, IWsMsgHandler> handlerMap = new HashMap<>();
    private static Logger log = LoggerFactory.getLogger(LeagueWsMsgHandler.class);
    public static final LeagueWsMsgHandler me = new LeagueWsMsgHandler();

    public LeagueWsMsgHandler() {
        handlerMap.put(MessageType.PING, new LeaguePingHandler());
        handlerMap.put(MessageType.CHAT, new LeagueChatHandler());
        handlerMap.put(MessageType.OTHER, new LeagueOtherHandler());
    }
    /**
     * 握手时走这个方法，业务可以在这里获取cookie，request参数等
     */
    @Override
    public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        String clientip = request.getClientIp();
        String uuid = IdUtil.objectId();
        String myname = request.getParam("name");
        Tio.bindUser(channelContext, myname);
        Tio.bindToken(channelContext, uuid);
        log.info("收到来自{}的ws握手包\r\n{}", clientip, request.toString());
        return httpResponse;
    }
    /**
     * @param httpRequest
     * @param httpResponse
     * @param channelContext
     * @throws Exception
     * @author tanyaowu
     */
    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        String region = httpRequest.getParam("region");
        if(StringUtils.isEmpty(region)){
            return;
        }
        //绑定到群组，后面会有群发
        Tio.bindGroup(channelContext, region);
        //获取当前大区有多少人在开黑大厅
        int i = Tio.groupCount(channelContext.tioConfig, region);



        String token = channelContext.getToken();
        Package pack = new Package();
        pack.setType(MessageType.OTHER);
        //构建一个发送other的token包
        pack.setData(JSON.toJSONString(OtherPak.builder().otherPakType(OtherPakType.SEND_TOKEN).data(token).build()));
        WsResponse wsResponse = WsResponse.fromText(JSON.toJSONString(pack), LeagueServerConfig.CHARSET);
        //发送给用户的id
        Tio.sendToToken(channelContext.tioConfig,token,wsResponse);
//        int count = Tio.getAllChannelContexts(channelContext.tioConfig).getObj().size();
//        String msg = "{name:'admin',message:'" + channelContext.userid + " 进来了，共【" + count + "】人在线" + "'}";
        //用tio-websocket，服务器发送到客户端的Packet都是WsResponse
//        WsResponse wsResponse = WsResponse.fromText(msg, LeagueServerConfig.CHARSET);
//        //群发
//        Tio.sendToGroup(channelContext.tioConfig, region, wsResponse);
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
}