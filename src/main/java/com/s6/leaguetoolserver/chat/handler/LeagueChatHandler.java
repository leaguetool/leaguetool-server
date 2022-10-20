package com.s6.leaguetoolserver.chat.handler;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.s6.leaguetoolserver.chat.config.LeagueServerConfig;
import com.s6.leaguetoolserver.chat.packages.ChatMessage;
import com.s6.leaguetoolserver.chat.packages.Package;
import com.s6.leaguetoolserver.component.ChatSetting;
import com.s6.leaguetoolserver.component.emoji.Emoji;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

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
//        content.replaceAll("\\[害怕\\]", "<emoji>#icon--scared</emoji>"
        //处理表情包
        emojiParse(chatMessage);
//        chatMessage.setContent(chatMessage.getContent());
        chatMessage.setId(IdUtil.objectId());
        aPackage.setData(JSON.toJSONString(chatMessage));
        WsResponse wsResponse = WsResponse.fromText(JSON.toJSONString(aPackage), LeagueServerConfig.CHARSET);
        //群发
        Tio.sendToGroup(channelContext.tioConfig, chatMessage.getRegion().getId().toString(), wsResponse);
        return null;
    }

    private void emojiParse(ChatMessage chatMessage){
        AtomicReference<String> content = new AtomicReference<>(chatMessage.getContent());
        //找出用了哪些表情包
        List<String> useEmoji = ReUtil.findAll("\\[([^\\s\\[\\]]+?)\\]", content.get(), 1, new ArrayList<String>()) ;
        if (useEmoji.isEmpty()) {
            return;
        }
        ChatSetting chatSetting = SpringUtil.getBean(ChatSetting.class);
        //装匹配到的emoji
        Map<String,String> reMap = new HashMap<>();
        for (Emoji emoji : chatSetting.getEmoji()) {
            List<Emoji> data = emoji.getData();
            data.forEach(item -> {
                reMap.put(item.getText(), item.getEmoji());
            });
        }
        if(reMap.isEmpty()){
            return;
        }
        useEmoji.forEach(item -> {
            String value = reMap.get(item);
            if(null != value){
                content.set(ReUtil.replaceAll(content.get(), "\\[(["+item+"\\[\\]]+?)\\]", "<emoji>"+value+"</emoji>"));
            }
        });
        chatMessage.setContent(content.get());
    }
}
