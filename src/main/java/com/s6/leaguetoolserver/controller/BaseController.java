package com.s6.leaguetoolserver.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.s6.leaguetoolserver.component.ChatSetting;
import com.s6.leaguetoolserver.model.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @NacosValue(value = "${notice:公告：本工具用于英雄联盟玩家开黑寻找队友}", autoRefreshed = true)
    private String notice;

    @Autowired
    ChatSetting chatSetting;

    @GetMapping("getNotice")
    public R getNotice(){
        return R.success(notice);
    }

    @GetMapping("getEmoji")
    public R getEmoji(){
        return R.success(chatSetting.getEmoji());
    }
}
