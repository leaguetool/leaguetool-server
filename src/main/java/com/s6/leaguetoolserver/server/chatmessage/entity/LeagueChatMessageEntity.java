package com.s6.leaguetoolserver.server.chatmessage.entity;

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
@TableName("league_chat_message")
@ApiModel(value = "LeagueChatMessageEntity对象", description = "")
public class LeagueChatMessageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("uid")
    @TableField("uid")
    private String uid;

    @ApiModelProperty("大区id")
    @TableField("user_area")
    private Integer userArea;

    @ApiModelProperty("大区名字")
    @TableField("user_area_name")
    private String userAreaName;

    @ApiModelProperty("消息内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("通道")
    @TableField("group")
    private Integer group;

    @ApiModelProperty("消息类型")
    @TableField("msg_type")
    private String msgType;

    @ApiModelProperty("头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("排位信息")
    @TableField("rank")
    private String rank;

    @ApiModelProperty("性别")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty("名字")
    @TableField("display_name")
    private String displayName;

    @ApiModelProperty("忽略这个字段")
    @TableField("is_self")
    private Integer isSelf;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标志")
    @TableField("delete")
    @TableLogic
    private Integer delete;
}
