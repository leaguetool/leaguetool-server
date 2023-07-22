package com.s6.leaguetoolserver.chat.handler;

import com.alibaba.fastjson.JSON;
import com.s6.leaguetoolserver.chat.config.LeagueServerConfig;
import com.s6.leaguetoolserver.chat.packages.Package;
import com.s6.leaguetoolserver.chat.packages.enums.MessageType;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.server.handler.IWsMsgHandler;

@Component
public class LeaguePingHandler implements IWsMsgHandler {

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
        Package pack = new Package();
        pack.setType(MessageType.PONG);
        pack.setData(null);
        WsResponse wsResponse = WsResponse.fromText(JSON.toJSONString(pack), LeagueServerConfig.CHARSET);
        //发送给用户的id
        Tio.sendToToken(channelContext.tioConfig,channelContext.getToken(),wsResponse);
        return null;
    }
}
