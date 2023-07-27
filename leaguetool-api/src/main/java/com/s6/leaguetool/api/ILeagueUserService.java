package com.s6.leaguetool.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.s6.leaguetool.model.LeagueUserEntity;

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
