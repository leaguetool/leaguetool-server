package com.s6.leaguetool.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import com.s6.leaguetool.model.enums.LogicDeleteEnum;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis-plus自动填充处理类
 */
@Slf4j
@Component
public class OrmMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入填充
     * @param metaObject 元对象
     */
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("version", 0L, metaObject);
        this.setFieldValByName("enable", LogicDeleteEnum.DEFAULT, metaObject);
    }

    /**
     * 更新填充
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
