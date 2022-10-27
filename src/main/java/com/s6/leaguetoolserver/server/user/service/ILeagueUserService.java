package com.s6.leaguetoolserver.server.user.service;

import com.s6.leaguetoolserver.server.user.entity.LeagueUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
public interface ILeagueUserService extends IService<LeagueUserEntity> {

    LeagueUserEntity getUserByUid(String uid);
}
