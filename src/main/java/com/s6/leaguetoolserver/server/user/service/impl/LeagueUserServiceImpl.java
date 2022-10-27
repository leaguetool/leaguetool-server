package com.s6.leaguetoolserver.server.user.service.impl;

import com.s6.leaguetoolserver.server.user.entity.LeagueUserEntity;
import com.s6.leaguetoolserver.server.user.mapper.LeagueUserMapper;
import com.s6.leaguetoolserver.server.user.service.ILeagueUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class LeagueUserServiceImpl extends ServiceImpl<LeagueUserMapper, LeagueUserEntity> implements ILeagueUserService {


    @Override
    public LeagueUserEntity getUserByUid(String uid) {

        return null;
    }
}
