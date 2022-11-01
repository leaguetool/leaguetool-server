package com.s6.leaguetoolserver.server.userinfo.controller;

import cn.hutool.system.UserInfo;
import com.s6.leaguetoolserver.config.exception.GlobalException;
import com.s6.leaguetoolserver.model.R;
import com.s6.leaguetoolserver.server.userinfo.dto.LoginDTO;
import com.s6.leaguetoolserver.server.userinfo.entity.LeagueUserInfoEntity;
import com.s6.leaguetoolserver.server.userinfo.service.ILeagueUserInfoService;
import jdk.nashorn.internal.objects.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
@RestController
@RequestMapping("/userinfo/leagueUserInfoEntity")
public class LeagueUserInfoController {

    @Autowired
    ILeagueUserInfoService leagueUserInfoService;


    @PostMapping("/login")
    public R<?> login(@RequestBody LoginDTO userinfo){
        try {
            return R.success(leagueUserInfoService.login(userinfo));
        } catch (GlobalException e) {
            return R.fail(e.getCode(),e.getMsg(), e.getCause());
        }
    }
}
