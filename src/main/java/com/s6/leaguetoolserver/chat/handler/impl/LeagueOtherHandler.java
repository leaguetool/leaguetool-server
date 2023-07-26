package com.s6.leaguetoolserver.chat.handler.impl;

import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSON;
import com.s6.leaguetoolserver.chat.commen.ChatUtils;
import com.s6.leaguetoolserver.chat.commen.HotUtils;
import com.s6.leaguetoolserver.chat.handler.AbstractHandler;
import com.s6.leaguetoolserver.chat.packages.OtherPak;
import com.s6.leaguetoolserver.chat.packages.Package;
import com.s6.leaguetoolserver.chat.packages.enums.HandlerType;
import com.s6.leaguetoolserver.chat.packages.enums.MessageType;
import com.s6.leaguetoolserver.chat.packages.enums.OtherPakType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.websocket.common.WsRequest;

import java.util.Set;

/**
 * @author cailong
 * OTHER 其他处理器, 用于处理其他消息
 */
@Component
@RequiredArgsConstructor
public class LeagueOtherHandler extends AbstractHandler {

    private final ChatUtils chatUtils;

    /**
     * 获取处理器类型 {@link HandlerType}
     * @return 处理器类型 {@link HandlerType}
     * @see HandlerType#OTHER
     * @see MessageType#OTHER
     */
    @Override
    public Pair<HandlerType, MessageType> getHandlerType() {
        return Pair.of(HandlerType.OTHER, MessageType.OTHER);
    }

    /**
     * 处理消息
     * @param wsRequest 请求 {@link WsRequest}
     * @param text 消息 {@link String}
     * @param channelContext 通道上下文 {@link ChannelContext}
     */
    @Override
    public void onMessage(WsRequest wsRequest, String text, ChannelContext channelContext) {
        Set<String> obj = channelContext.getGroups().getObj();
        Package pack = JSON.parseObject(text, Package.class);
        OtherPak otherPak = JSON.parseObject(pack.getData(), OtherPak.class);
        switch (otherPak.getOtherPakType()) {
            //切换大区后也要把hot数据返回去
            case CHANGE_REGION:
                //取消绑定之前的组
                obj.stream().findFirst().ifPresent(s -> Tio.unbindGroup(s, channelContext));
                //绑定到新的组
                Tio.bindGroup(channelContext, otherPak.getData());
                int i = Tio.groupCount(channelContext.tioConfig, otherPak.getData());
                int hot = HotUtils.getHot(i);
                //发送给大区的人
                chatUtils.send(channelContext, OtherPakType.AREA_HOT,hot);
                //同步聊天记录
                chatUtils.sendChatHistory(channelContext, otherPak.getData());
            case BASE_DATA:
                //发送基础信息
                chatUtils.initBaseInfo(channelContext);
        }
    }
}
