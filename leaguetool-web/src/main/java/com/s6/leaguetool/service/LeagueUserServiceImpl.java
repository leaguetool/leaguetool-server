package com.s6.leaguetool.service;

import com.s6.leaguetool.api.ILeagueUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.s6.leaguetool.mapper.LeagueUserMapper;
import com.s6.leaguetool.model.LeagueUserEntity;
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
        return this.baseMapper.selectOne(new QueryWrapper<LeagueUserEntity>().eq(LeagueUserEntity.UID, uid));
    }
}
