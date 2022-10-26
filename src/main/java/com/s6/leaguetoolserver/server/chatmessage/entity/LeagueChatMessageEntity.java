package com.s6.leaguetoolserver.server.chatmessage.entity;

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
@TableName("league_chat_message")
@ApiModel(value = "LeagueChatMessageEntity对象", description = "")
public class LeagueChatMessageEntity extends BaseEntity {

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
    @TableField("chat_group")
    private Integer chatGroup;

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

    public static final String ID = "id";

    public static final String UID = "uid";

    public static final String USER_AREA = "user_area";

    public static final String USER_AREA_NAME = "user_area_name";

    public static final String CONTENT = "content";

    public static final String CHAT_GROUP = "chat_group";

    public static final String MSG_TYPE = "msg_type";

    public static final String AVATAR = "avatar";

    public static final String RANK = "rank";

    public static final String GENDER = "gender";

    public static final String DISPLAY_NAME = "display_name";

    public static final String IS_SELF = "is_self";
}
