package com.s6.leaguetoolserver.controller;

import com.s6.leaguetoolserver.model.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaseController {

    @Value(value = "${notice:公告：本工具用于英雄联盟玩家开黑寻找队友}")
    private String notice;

    @GetMapping("getNotice")
    public R getNotice(){
        return R.success(notice);
    }


}
