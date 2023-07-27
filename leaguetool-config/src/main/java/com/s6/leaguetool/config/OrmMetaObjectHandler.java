package com.s6.leaguetool.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import com.s6.leaguetool.model.enums.LogicDeleteEnum;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class OrmMetaObjectHandler implements MetaObjectHandler {

    public void insertFill(MetaObject metaObject) {
//        log.info("插入时自动填充...");
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("version", 0L, metaObject);
        this.setFieldValByName("enable", LogicDeleteEnum.DEFAULT, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        log.info("更新时自动填充...");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
