package com.s6.leaguetool.controller;

import com.s6.leaguetool.chat.commen.ChatSetting;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.s6.leaguetool.emoji.Emoji;
import com.s6.leaguetool.model.R;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BaseController {

    @NacosValue(value = "${notice:公告：本工具用于英雄联盟玩家开黑寻找队友}", autoRefreshed = true)
    private String notice;

    private final ChatSetting chatSetting;

    /**
     * 获取公告
     * @return 公告
     */
    @GetMapping("getNotice")
    public R<String> getNotice(){
        return R.success(notice);
    }

    /**
     * 获取emoji
     * @return emoji
     */
    @GetMapping("getEmoji")
    public R<List<Emoji>> getEmoji(){
        return R.success(chatSetting.getEmoji());
    }

    /**
     * 获取chat配置
     * @return chat配置
     */
    @GetMapping("getSetting")
    public R<ChatSetting> getSetting(){
        return R.success(chatSetting);
    }
}
