package com.s6.leaguetoolserver.server.userinfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
@TableName("league_user_info")
@ApiModel(value = "LeagueUserInfo对象", description = "")
public class LeagueUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty("uid")
    private String uid;

    @ApiModelProperty("大区id")
    private Integer area;

    @ApiModelProperty("大区名字")
    private String areaName;

    @ApiModelProperty("昵称")
    private String displayName;

    @ApiModelProperty("等级")
    private String summonerLevel;

    @ApiModelProperty("头像")
    private String summonerAvatar;

    @ApiModelProperty("单双段位")
    private String summonerRankSolo;

    @ApiModelProperty("灵活段位")
    private String summonerRankGroup;

    @ApiModelProperty("胜率")
    private String winRate;

    @ApiModelProperty("逻辑删除")
    private Integer delete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(String summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String getSummonerAvatar() {
        return summonerAvatar;
    }

    public void setSummonerAvatar(String summonerAvatar) {
        this.summonerAvatar = summonerAvatar;
    }

    public String getSummonerRankSolo() {
        return summonerRankSolo;
    }

    public void setSummonerRankSolo(String summonerRankSolo) {
        this.summonerRankSolo = summonerRankSolo;
    }

    public String getSummonerRankGroup() {
        return summonerRankGroup;
    }

    public void setSummonerRankGroup(String summonerRankGroup) {
        this.summonerRankGroup = summonerRankGroup;
    }

    public String getWinRate() {
        return winRate;
    }

    public void setWinRate(String winRate) {
        this.winRate = winRate;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "LeagueUserInfo{" +
            "id = " + id +
            ", uid = " + uid +
            ", area = " + area +
            ", areaName = " + areaName +
            ", displayName = " + displayName +
            ", summonerLevel = " + summonerLevel +
            ", summonerAvatar = " + summonerAvatar +
            ", summonerRankSolo = " + summonerRankSolo +
            ", summonerRankGroup = " + summonerRankGroup +
            ", winRate = " + winRate +
            ", delete = " + delete +
        "}";
    }
}
