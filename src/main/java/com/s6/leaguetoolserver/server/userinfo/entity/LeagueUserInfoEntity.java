package com.s6.leaguetoolserver.server.userinfo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class LeagueUserInfoEntity implements Serializable {

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

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除")
    @TableField("delete")
    @TableLogic
    private Integer delete;
}
