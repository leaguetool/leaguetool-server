package com.s6.leaguetool.model.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.s6.leaguetool.model.enums.LogicDeleteEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类，包含基础属性
 **/
@Data
public class BaseEntity implements Serializable {


    private static final long serialVersionUID = -5695919579047289592L;
    /**
     * 创建时间
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 最后更新时间
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 乐观锁字段
     */
//    @JsonIgnore
//    @TableField(fill = FieldFill.INSERT)
//    @Version
//    private Long version;

    /**
     * 逻辑删除(默认0,1表示删除)
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private LogicDeleteEnum enable;

}
