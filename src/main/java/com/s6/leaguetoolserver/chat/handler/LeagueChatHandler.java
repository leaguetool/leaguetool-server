package com.s6.leaguetoolserver.chat.handler;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.s6.leaguetoolserver.chat.commen.ChatUtils;
import com.s6.leaguetoolserver.chat.config.LeagueServerConfig;
import com.s6.leaguetoolserver.chat.packages.ChatMessage;
import com.s6.leaguetoolserver.chat.packages.Package;
import com.s6.leaguetoolserver.component.ChatSetting;
import com.s6.leaguetoolserver.component.emoji.Emoji;
import com.s6.leaguetoolserver.enums.UserStatusEnum;
import com.s6.leaguetoolserver.server.user.entity.LeagueUserEntity;
import com.s6.leaguetoolserver.server.user.service.ILeagueUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.utils.lock.SetWithLock;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.server.WsServerStarter;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.s6.leaguetoolserver.chat.packages.enums.MessageType.CHAT;

@Component
public class LeagueChatHandler implements IWsMsgHandler {

    //创建日志工厂
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LeagueChatHandler.class);

    @Autowired
    ChatUtils chatUtils;

    @Autowired
    ILeagueUserService leagueUserService;

    @Autowired
    WsServerStarter serverStarter;

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
        Package pack = JSON.parseObject(text, Package.class);
        ChatMessage chatMessage = JSON.parseObject(pack.getData(), ChatMessage.class);

        //如果检测账号异常，那么就不继续往下处理
        boolean checkUserStatus = checkUserStatus(chatMessage.getUid());
        if(checkUserStatus) {
            return null;
        }
        //处理聊天信息并且发送
        chatInformationProcess(pack, chatMessage, channelContext);
        //保存数据
        chatUtils.saveMessage(chatMessage);
        return null;
    }

    /**
     * 发送聊天消息
     * @param pack 包
     */
    public Pair<Boolean, String> sendChatMsg(Package pack){
        //聊天信息有异常直接返回
        if(pack == null || StringUtils.isEmpty(pack.getData()) || CHAT != pack.getType()){
            return Pair.of(false, "发送失败,消息数据不正确");
        }

        ChatMessage chatMessage = JSON.parseObject(pack.getData(), ChatMessage.class);
        SetWithLock<ChannelContext> userChannelContext = Tio.getByUserid(serverStarter.getServerTioConfig(), chatMessage.getUid());
        //判断用户是否在线
        if(userChannelContext == null || userChannelContext.getObj().size() == 0){
            return Pair.of(false, "发送失败,当前用户不在线");
        }
        //取出用户的channel
        ChannelContext channelContext = userChannelContext.getObj().iterator().next();

        //如果检测账号异常，那么就不继续往下处理
        boolean checkUserStatus = checkUserStatus(chatMessage.getUid());
        if(checkUserStatus) {
            return Pair.of(false, "发送失败,当前账号异常");
        }
        try{
            //处理聊天信息并且发送
            chatInformationProcess(pack, chatMessage, channelContext);
            //保存数据
            chatUtils.saveMessage(chatMessage);
        }catch (Exception e){
            log.error("发送聊天信息异常:{}", e.getMessage());
            return Pair.of(false, "发送失败,系统异常");
        }
        return Pair.of(true, "发送成功");
    }

    /**
     * 处理聊天信息并且群发发送
     * @param pack 包
     * @param chatMessage   聊天信息
     * @param channelContext    channel
     */
    private void chatInformationProcess(Package pack, ChatMessage chatMessage, ChannelContext channelContext) {
        //处理表情包
        emojiParse(chatMessage);
        chatMessage.setId(IdUtil.objectId());
        pack.setData(JSON.toJSONString(chatMessage));
        WsResponse wsResponse = WsResponse.fromText(JSON.toJSONString(pack), LeagueServerConfig.CHARSET);
        //群发
        Tio.sendToGroup(channelContext.tioConfig, chatMessage.getRegion().getId().toString(), wsResponse);
    }

    /**
     * 检测用户状态
     * @return  true:异常  false:正常
     */
    public boolean checkUserStatus(String uid){
        LeagueUserEntity user = leagueUserService.getUserByUid(uid);
        if(null == user){
            return true;
        }
        return UserStatusEnum.ABNORMAL.getCode() == user.getStatus().getCode();
    }



    /**
     * 解析表情
     * @param chatMessage chat实体
     */
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
