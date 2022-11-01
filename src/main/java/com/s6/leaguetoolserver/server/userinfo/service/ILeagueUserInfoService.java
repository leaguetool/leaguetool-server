package com.s6.leaguetoolserver.server.userinfo.service;

import com.s6.leaguetoolserver.server.userinfo.dto.LoginDTO;
import com.s6.leaguetoolserver.server.userinfo.dto.LoginResult;
import com.s6.leaguetoolserver.server.userinfo.entity.LeagueUserInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
public interface ILeagueUserInfoService extends IService<LeagueUserInfoEntity> {

    LoginResult login(LoginDTO userinfo);
}
