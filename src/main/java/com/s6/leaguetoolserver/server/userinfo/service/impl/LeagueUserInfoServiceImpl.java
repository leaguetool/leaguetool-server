package com.s6.leaguetoolserver.server.userinfo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.s6.leaguetoolserver.config.exception.GlobalException;
import com.s6.leaguetoolserver.enums.UserStatusEnum;
import com.s6.leaguetoolserver.model.LeaguePlayerInfo;
import com.s6.leaguetoolserver.server.user.entity.LeagueUserEntity;
import com.s6.leaguetoolserver.server.user.service.ILeagueUserService;
import com.s6.leaguetoolserver.server.userinfo.dto.LoginDTO;
import com.s6.leaguetoolserver.server.userinfo.dto.LoginResult;
import com.s6.leaguetoolserver.server.userinfo.entity.LeagueUserInfoEntity;
import com.s6.leaguetoolserver.server.userinfo.mapper.LeagueUserInfoMapper;
import com.s6.leaguetoolserver.server.userinfo.service.ILeagueUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public LoginResult login(LoginDTO userinfo) throws GlobalException{
        LeagueUserEntity leagueUserEntity = new LeagueUserEntity();
//
        String infoData = userinfo.getInfoData();
        JSONObject jsonObject = JSON.parseObject(infoData);
        String uid = jsonObject.getString(LeagueUserEntity.UID);
        if(StringUtils.hasText(uid)){
            LeagueUserEntity leagueUser = userService.getUserByUid(uid);
            //如果账号不存在就保存一下
            if(null == leagueUser){
                leagueUserEntity.setUid(uid);
                userService.save(leagueUserEntity);
            }else{
                if(UserStatusEnum.ABNORMAL.getCode() == leagueUser.getStatus().getCode()) throw new GlobalException("账号被冻结无法使用");
            }
        }
        //大区
        String area = jsonObject.getString(LeagueUserInfoEntity.AREA);
        //查看个人资料是否有该大区的信息
        LeagueUserInfoEntity leagueUserInfo = this.baseMapper.selectOne(new QueryWrapper<LeagueUserInfoEntity>().eq(LeagueUserInfoEntity.UID, uid).eq(LeagueUserInfoEntity.AREA, area));
        LeagueUserInfoEntity leagueUserInfoEntity = jsonObject.toJavaObject(LeagueUserInfoEntity.class);
        LeaguePlayerInfo leaguePlayerInfo = JSON.parseObject(userinfo.getPlayerInfo()).toJavaObject(LeaguePlayerInfo.class);
        leagueUserInfoEntity.setSummonerLevel(String.valueOf(leaguePlayerInfo.getLevel()));
        leagueUserInfoEntity.setSummonerAvatar(String.valueOf(leaguePlayerInfo.getIconId()));

        BeanUtils.copyProperties(userinfo, leagueUserInfoEntity);
        if (null == leagueUserInfo){
            this.save(leagueUserInfoEntity);
        }else{
            String id = leagueUserInfo.getId();
            BeanUtils.copyProperties(leagueUserInfoEntity, leagueUserInfo);
            leagueUserInfo.setId(id);
            this.updateById(leagueUserInfo);
        }

        return null;
    }
}
