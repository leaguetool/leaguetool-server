package com.s6.leaguetoolserver.server.chatmessage.controller;

import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSON;
import com.s6.leaguetoolserver.chat.handler.impl.LeagueChatHandler;
import com.s6.leaguetoolserver.chat.packages.Package;
import com.s6.leaguetoolserver.model.R;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.s6.leaguetoolserver.chat.packages.enums.MessageType.CHAT;

/**
 * <p>
 *  chat前端控制器
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
@RestController
@RequestMapping("/chat")
public class LeagueChatMessageController {
    private static final Logger log = LoggerFactory.getLogger(LeagueChatMessageController.class);

    LeagueChatHandler leagueChatHandler;
    @Autowired
    LeagueChatMessageController(LeagueChatHandler leagueChatHandler){
        this.leagueChatHandler = leagueChatHandler;
    }

    /**
     * 实现发送聊天消息方法
     */
    @PostMapping("/sendChatMessage")
    public R<String> sendChatMessage(@RequestBody Package pack){
        String packJsonStr = JSON.toJSONString(pack);
        log.info("chat/sendChatMessage: {}", packJsonStr);
        //聊天信息有异常直接返回
        if(pack == null || StringUtils.isEmpty(pack.getData()) || CHAT != pack.getType()){
            return R.err("聊天信息有误，请核实后再发送");
        }
        Pair<Boolean, String> pair = this.leagueChatHandler.sendChatMsg(pack);
        return R.of(pair);
    }
}
