package com.s6.leaguetoolserver.server.userinfo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 8035103770386872887L;

    //掌上
    private String mobilePlayerInfo;
    //战斗信息
    private String playerBattleSummary;
    //荣誉信息
    private String playerHonor;
    //基本信息
    private String playerInfo;
    //排位信息
    private String playerRankInfo;
    //基础信息
    private String infoData;
}
