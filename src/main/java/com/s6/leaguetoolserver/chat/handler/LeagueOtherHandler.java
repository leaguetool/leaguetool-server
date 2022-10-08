package com.s6.leaguetoolserver.chat.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.s6.leaguetoolserver.chat.packages.ChatMessage;
import com.s6.leaguetoolserver.chat.packages.OtherPak;
import com.s6.leaguetoolserver.chat.packages.Package;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.Set;

public class LeagueOtherHandler implements IWsMsgHandler {

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
        Set<String> obj = channelContext.getGroups().getObj();
        Package aPackage = JSON.parseObject(text, Package.class);
        OtherPak otherPak = JSON.parseObject(aPackage.getData(), OtherPak.class);
        switch (otherPak.getOtherPakType()) {
            case CHANGE_REGION:
                //取消绑定之前的组
                Tio.unbindGroup(obj.stream().findFirst().get(),channelContext);
                //绑定到新的组
                Tio.bindGroup(channelContext, otherPak.getData());
        }
        return null;
    }
}
