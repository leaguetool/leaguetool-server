package com.s6.leaguetoolserver.chat.commen;

import com.alibaba.fastjson.JSON;
import com.s6.leaguetoolserver.chat.config.LeagueServerConfig;
import com.s6.leaguetoolserver.chat.packages.*;
import com.s6.leaguetoolserver.chat.packages.Package;
import com.s6.leaguetoolserver.chat.packages.enums.MessageType;
import com.s6.leaguetoolserver.chat.packages.enums.OtherPakType;
import com.s6.leaguetoolserver.component.AdminUsers;
import com.s6.leaguetoolserver.component.ChatSetting;
import com.s6.leaguetoolserver.model.User;
import com.s6.leaguetoolserver.server.chatmessage.entity.LeagueChatMessageEntity;
import com.s6.leaguetoolserver.server.chatmessage.service.ILeagueChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.utils.lock.SetWithLock;
import org.tio.websocket.common.WsResponse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 聊天工具类
 * 用于发送消息
 * 用于发送基础信息
 * 用于发送token
 * 用于发送当前大区热度
 * 用于发送历史记录
 * 用于持久化聊天
 */
@Component
@RequiredArgsConstructor
public class ChatUtils {

    private final AdminUsers adminUsers;
    private final ChatSetting chatSetting;
    private final ILeagueChatMessageService chatMessageService;

    /**
     * 构建基础信息发送
     * @param channelContext 通道上下文
     */
    public void initBaseInfo(ChannelContext channelContext){
        BaseInfo baseInfo = new BaseInfo();
        List<User> admins = adminUsers.getUsers().stream().peek(user -> {
            SetWithLock<ChannelContext> setWithLock = Tio.getByUserid(channelContext.getTioConfig(), user.getUid());
            user.setOnline(null != setWithLock);
        }).collect(Collectors.toList());
        //管理员
        baseInfo.setAdmins(admins);
        //热词
        baseInfo.setHotWords(chatSetting.getHotWords());
        //发送基础信息
        this.send(channelContext, OtherPakType.BASE_DATA,baseInfo);
    }

    /**
     * 发送token
     * @param channelContext 上下文
     */
    public void sendToken(ChannelContext channelContext){
        String token = channelContext.getToken();
        //构建一个发送other的token包
        Package pack = Package.of(MessageType.OTHER, JSON.toJSONString(OtherPak.builder().otherPakType(OtherPakType.SEND_TOKEN).data(token).build()));
        WsResponse wsResponse = WsResponse.fromText(JSON.toJSONString(pack), LeagueServerConfig.CHARSET);
        //发送给用户的id
        Tio.sendToToken(channelContext.tioConfig,token,wsResponse);
    }

    /**
     * 发送当前大区热度
     * @param channelContext 上下文
     */
    public void sendInitHot(ChannelContext channelContext,String region){
        int i = Tio.groupCount(channelContext.tioConfig, region);
        int hot = HotUtils.getHot(i);
        //发送给大区的人
        this.send(channelContext, OtherPakType.AREA_HOT,hot);
    }

    /**
     * 发送历史记录
     * @param channelContext 上下文
     * @param region 大区id
     */
    public void sendChatHistory(ChannelContext channelContext, String region){
        List<ChatMessage> chatHistory = chatMessageService.getChatHistory(region, chatSetting.getHistoryCount()).stream().map(ChatMessage::new).collect(Collectors.toList());
        //因为查询时用的时间倒叙，这里需要把数据反转才是正常的聊天记录
        Collections.reverse(chatHistory);
        this.send(channelContext,OtherPakType.CHAT_HISTORY, chatHistory);
    }

    /**
     * 用other推送信息给用户
     * @param channelContext 通道上下文
     * @param otherPakType 发送的类型
     * @param data 要发送的数据
     */
    public void send(ChannelContext channelContext,OtherPakType otherPakType, Object data){
        //构建一个发送other的token包
        Package pack = Package.of(MessageType.OTHER, JSON.toJSONString(OtherPak.builder().otherPakType(otherPakType).data(JSON.toJSONString(data)).build()));
        WsResponse wsResponse = WsResponse.fromText(JSON.toJSONString(pack), LeagueServerConfig.CHARSET);
        //发送给用户的id
        Tio.sendToToken(channelContext.tioConfig,channelContext.getToken(),wsResponse);
    }

    /**
     * 持久化聊天
     * @param chatMessage 聊天消息
     */
    @Async
    public void saveMessage(ChatMessage chatMessage){
        LeagueChatMessageEntity messageEntity = new LeagueChatMessageEntity();
        Region region = chatMessage.getRegion();
        messageEntity.setUid(chatMessage.getUid());
        messageEntity.setDisplayName(chatMessage.getName());
        messageEntity.setMsgType(chatMessage.getType());
        messageEntity.setUserArea(region.getId());
        messageEntity.setUserAreaName(region.getName());
        messageEntity.setChatGroup(region.getId());
        messageEntity.setAvatar(chatMessage.getAvatar());
        messageEntity.setContent(chatMessage.getContent());
        messageEntity.setRank(chatMessage.getRank());
        chatMessageService.addMessage(messageEntity);
    }
}
