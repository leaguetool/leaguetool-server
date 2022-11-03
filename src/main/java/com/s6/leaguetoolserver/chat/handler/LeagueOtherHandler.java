package com.s6.leaguetoolserver.chat.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.s6.leaguetoolserver.chat.commen.ChatUtils;
import com.s6.leaguetoolserver.chat.commen.HotUtils;
import com.s6.leaguetoolserver.chat.config.LeagueServerConfig;
import com.s6.leaguetoolserver.chat.packages.ChatMessage;
import com.s6.leaguetoolserver.chat.packages.OtherPak;
import com.s6.leaguetoolserver.chat.packages.Package;
import com.s6.leaguetoolserver.chat.packages.enums.MessageType;
import com.s6.leaguetoolserver.chat.packages.enums.OtherPakType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.Set;

@Component
public class LeagueOtherHandler implements IWsMsgHandler {

    @Autowired
    ChatUtils chatUtils;
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
            //切换大区后也要把hot数据返回去
            case CHANGE_REGION:
                //取消绑定之前的组
                Tio.unbindGroup(obj.stream().findFirst().get(),channelContext);
                //绑定到新的组
                Tio.bindGroup(channelContext, otherPak.getData());
                int i = Tio.groupCount(channelContext.tioConfig, otherPak.getData());
                int hot = HotUtils.getHot(i);
                //发送给大区的人
                this.send(channelContext, OtherPakType.AREA_HOT,hot);
                //同步聊天记录
                chatUtils.sendChatHistory(channelContext, otherPak.getData());
            case BASE_DATA:
                //发送基础信息
                chatUtils.initBaseInfo(channelContext);
        }
        return null;
    }

    /**
     * 用other推送信息给用户
     * @param channelContext 通道上下文
     * @param otherPakType 发送的类型
     * @param data 要发送的数据
     */
    public void send(ChannelContext channelContext,OtherPakType otherPakType, Object data){
        Package pack = new Package();
        pack.setType(MessageType.OTHER);
        //构建一个发送other的token包
        pack.setData(JSON.toJSONString(OtherPak.builder().otherPakType(otherPakType).data(JSON.toJSONString(data)).build()));
        WsResponse wsResponse = WsResponse.fromText(JSON.toJSONString(pack), LeagueServerConfig.CHARSET);
        //发送给用户的id
        Tio.sendToToken(channelContext.tioConfig,channelContext.getToken(),wsResponse);
    }
}
