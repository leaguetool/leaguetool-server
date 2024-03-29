package com.s6.leaguetool.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.s6.leaguetool.model.LeagueUserInfoEntity;
import com.s6.leaguetool.model.dto.LoginDTO;
import com.s6.leaguetool.model.dto.LoginResult;

/**
 * <p>
 *  用户信息服务类
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
public interface ILeagueUserInfoService extends IService<LeagueUserInfoEntity> {
    /**
     * 登录
     * @param userinfo 用户信息
     * @return LoginResult
     */
    LoginResult login(LoginDTO userinfo);
}
