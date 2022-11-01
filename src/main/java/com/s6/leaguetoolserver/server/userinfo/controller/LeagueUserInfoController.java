package com.s6.leaguetoolserver.server.userinfo.controller;

import cn.hutool.system.UserInfo;
import com.s6.leaguetoolserver.model.R;
import com.s6.leaguetoolserver.server.userinfo.dto.LoginDTO;
import com.s6.leaguetoolserver.server.userinfo.entity.LeagueUserInfoEntity;
import com.s6.leaguetoolserver.server.userinfo.service.ILeagueUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
@Controller
@RequestMapping("/userinfo/leagueUserInfoEntity")
public class LeagueUserInfoController {

    @Autowired
    ILeagueUserInfoService leagueUserInfoService;


    @PostMapping("/login")
    public R<LeagueUserInfoEntity> login(@RequestBody LoginDTO userinfo){
        return R.success(leagueUserInfoService.login(userinfo));
    }
}
