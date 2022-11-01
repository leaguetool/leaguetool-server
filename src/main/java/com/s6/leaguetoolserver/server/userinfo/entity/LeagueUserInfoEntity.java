package com.s6.leaguetoolserver.server.userinfo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.s6.leaguetoolserver.model.base.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
@Getter
@Setter
@TableName("league_user_info")
@ApiModel(value = "LeagueUserInfoEntity对象", description = "")
public class LeagueUserInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("uid")
    @TableField("uid")
    private String uid;

    @ApiModelProperty("大区id")
    @TableField("area")
    private Integer area;

    @ApiModelProperty("大区名字")
    @TableField("area_name")
    private String areaName;

    @ApiModelProperty("昵称")
    @TableField("display_name")
    private String displayName;

    @ApiModelProperty("等级")
    @TableField("summoner_level")
    private String summonerLevel;

    @ApiModelProperty("头像")
    @TableField("summoner_avatar")
    private String summonerAvatar;

    @ApiModelProperty("单双段位")
    @TableField("summoner_rank_solo")
    private String summonerRankSolo;

    @ApiModelProperty("灵活段位")
    @TableField("summoner_rank_group")
    private String summonerRankGroup;

    @ApiModelProperty("胜率")
    @TableField("win_rate")
    private String winRate;

    //掌上
    @ApiModelProperty("掌上")
    @TableField("mobile_player_info")
    private String mobilePlayerInfo;
    //战斗信息
    @ApiModelProperty("战斗信息")
    @TableField("player_battle_summary")
    private String playerBattleSummary;
    //荣誉信息
    @ApiModelProperty("荣誉信息")
    @TableField("player_honor")
    private String playerHonor;
    //基本信息
    @ApiModelProperty("基本信息")
    @TableField("player_info")
    private String playerInfo;
    //排位信息
    @ApiModelProperty("排位信息")
    @TableField("player_rank_info")
    private String playerRankInfo;
    //基础信息
    @ApiModelProperty("基础信息")
    @TableField("info_data")
    private String infoData;

    public static final String ID = "id";

    public static final String UID = "uid";

    public static final String AREA = "area";

    public static final String AREA_NAME = "area_name";
    //驼峰
    public static final String AREANAME = "areaName";

    public static final String DISPLAY_NAME = "display_name";

    public static final String SUMMONER_LEVEL = "summoner_level";

    public static final String SUMMONER_AVATAR = "summoner_avatar";

    public static final String SUMMONER_RANK_SOLO = "summoner_rank_solo";

    public static final String SUMMONER_RANK_GROUP = "summoner_rank_group";

    public static final String WIN_RATE = "win_rate";
}
