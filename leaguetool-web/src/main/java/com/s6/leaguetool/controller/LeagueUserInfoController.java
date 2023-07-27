package com.s6.leaguetool.controller;

import com.s6.leaguetool.api.ILeagueUserInfoService;
import com.s6.leaguetool.config.exception.GlobalException;
import com.s6.leaguetool.model.R;
import com.s6.leaguetool.model.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequiredArgsConstructor
public class LeagueUserInfoController {

    private final ILeagueUserInfoService leagueUserInfoService;


    @PostMapping("/login")
    public R<?> login(@RequestBody LoginDTO userinfo){
        try {
            return R.success(leagueUserInfoService.login(userinfo));
        } catch (GlobalException e) {
            return R.fail(e.getCode(),e.getMsg(), e.getCause());
        }
    }
}
