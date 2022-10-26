package com.s6.leaguetoolserver.server.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.s6.leaguetoolserver.model.base.BaseEntity;
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
@TableName("league_user")
@ApiModel(value = "LeagueUserEntity对象", description = "")
public class LeagueUserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("uid")
    @TableField("uid")
    private String uid;

    @ApiModelProperty("用户状态 0正常 -1冻结")
    @TableField("status")
    private Integer status;

    public static final String ID = "id";

    public static final String UID = "uid";

    public static final String STATUS = "status";
}
