package com.s6.leaguetoolserver.chat.commen;

import cn.hutool.extra.spring.SpringUtil;
import com.s6.leaguetoolserver.chat.handler.LeagueOtherHandler;
import com.s6.leaguetoolserver.chat.handler.LeagueWsMsgHandler;
import com.s6.leaguetoolserver.chat.packages.BaseInfo;
import com.s6.leaguetoolserver.chat.packages.enums.MessageType;
import com.s6.leaguetoolserver.chat.packages.enums.OtherPakType;
import com.s6.leaguetoolserver.component.AdminUsers;
import com.s6.leaguetoolserver.component.ChatSetting;
import com.s6.leaguetoolserver.model.User;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.utils.lock.SetWithLock;

import java.util.List;
import java.util.stream.Collectors;

public class ChatUtils {

    /**
     * 构建基础信息发送
     * @param channelContext 通道上下文
     */
    public static void initBaseInfo(ChannelContext channelContext){
        LeagueOtherHandler leagueOtherHandler = (LeagueOtherHandler) LeagueWsMsgHandler.handlerMap.get(MessageType.OTHER);
        BaseInfo baseInfo = new BaseInfo();
        AdminUsers bean = SpringUtil.getBean(AdminUsers.class);
        ChatSetting chatSetting = SpringUtil.getBean(ChatSetting.class);
        List<User> admins = bean.getUsers().stream().map(user -> {
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
}
