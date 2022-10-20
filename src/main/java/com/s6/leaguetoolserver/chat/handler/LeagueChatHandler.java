package com.s6.leaguetoolserver.chat.handler;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.s6.leaguetoolserver.chat.config.LeagueServerConfig;
import com.s6.leaguetoolserver.chat.packages.ChatMessage;
import com.s6.leaguetoolserver.chat.packages.Package;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.Set;

public class LeagueChatHandler implements IWsMsgHandler {

    @Override
    public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
        return null;
    }

    @Override
    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {

    }

    @Override
    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        return null;
    }

    @Override
    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
        return null;
    }

    @Override
    public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
        //聊天
        Package aPackage = JSON.parseObject(text, Package.class);
        ChatMessage chatMessage = JSON.parseObject(aPackage.getData(), ChatMessage.class);
        String content = chatMessage.getContent();
        chatMessage.setContent(content.replaceAll("\\[害怕\\]", "<emoji>#icon--scared</emoji>"));
        chatMessage.setId(IdUtil.objectId());
        aPackage.setData(JSON.toJSONString(chatMessage));
        WsResponse wsResponse = WsResponse.fromText(JSON.toJSONString(aPackage), LeagueServerConfig.CHARSET);
        //群发
        Tio.sendToGroup(channelContext.tioConfig, chatMessage.getRegion().getId().toString(), wsResponse);
        return null;
    }
}
