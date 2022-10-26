package com.s6.leaguetoolserver.chat.commen;

import cn.hutool.extra.spring.SpringUtil;
import com.s6.leaguetoolserver.chat.handler.LeagueOtherHandler;
import com.s6.leaguetoolserver.chat.handler.LeagueWsMsgHandler;
import com.s6.leaguetoolserver.chat.packages.BaseInfo;
import com.s6.leaguetoolserver.chat.packages.ChatMessage;
import com.s6.leaguetoolserver.chat.packages.Region;
import com.s6.leaguetoolserver.chat.packages.enums.MessageType;
import com.s6.leaguetoolserver.chat.packages.enums.OtherPakType;
import com.s6.leaguetoolserver.component.AdminUsers;
import com.s6.leaguetoolserver.component.ChatSetting;
import com.s6.leaguetoolserver.model.User;
import com.s6.leaguetoolserver.server.chatmessage.entity.LeagueChatMessageEntity;
import com.s6.leaguetoolserver.server.chatmessage.service.ILeagueChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.utils.lock.SetWithLock;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChatUtils {

    @Autowired
    AdminUsers adminUsers;
    @Autowired
    ChatSetting chatSetting;
    @Autowired
    LeagueOtherHandler leagueOtherHandler;
    @Autowired
    ILeagueChatMessageService chatMessageService;

    /**
     * 构建基础信息发送
     * @param channelContext 通道上下文
     */
    public void initBaseInfo(ChannelContext channelContext){
        BaseInfo baseInfo = new BaseInfo();
        List<User> admins = adminUsers.getUsers().stream().map(user -> {
            SetWithLock<ChannelContext> setWithLock = Tio.getByUserid(channelContext.getTioConfig(), user.getUid());
            user.setOnline(null != setWithLock);
            return user;
        }).collect(Collectors.toList());
        //管理员
        baseInfo.setAdmins(admins);
        //热词
        baseInfo.setHotWords(chatSetting.getHotWords());
        //发送基础信息
        leagueOtherHandler.send(channelContext, OtherPakType.BASE_DATA,baseInfo);
    }

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
