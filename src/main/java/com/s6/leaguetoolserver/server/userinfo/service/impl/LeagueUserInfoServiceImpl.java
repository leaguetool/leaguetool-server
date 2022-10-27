package com.s6.leaguetoolserver.server.userinfo.service.impl;

import com.s6.leaguetoolserver.server.user.service.ILeagueUserService;
import com.s6.leaguetoolserver.server.userinfo.dto.LoginResult;
import com.s6.leaguetoolserver.server.userinfo.entity.LeagueUserInfoEntity;
import com.s6.leaguetoolserver.server.userinfo.mapper.LeagueUserInfoMapper;
import com.s6.leaguetoolserver.server.userinfo.service.ILeagueUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
@Primary
@Service
public class LeagueUserInfoServiceImpl extends ServiceImpl<LeagueUserInfoMapper, LeagueUserInfoEntity> implements ILeagueUserInfoService {

    @Autowired
    ILeagueUserService userService;


    @Override
    public LoginResult login(LeagueUserInfoEntity userinfo) {



        return null;
    }
}
