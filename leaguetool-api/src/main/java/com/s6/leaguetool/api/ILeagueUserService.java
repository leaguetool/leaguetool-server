package com.s6.leaguetool.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.s6.leaguetool.model.LeagueUserEntity;

/**
 * 用户服务类
 * @author cailong
 * @since 2022-10-26
 */
public interface ILeagueUserService extends IService<LeagueUserEntity> {
    /**
     * 根据uid获取用户信息
     * @param uid 用户uid
     * @return LeagueUserEntity
     */
    LeagueUserEntity getUserByUid(String uid);
}
