package com.s6.leaguetool.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户状态
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum implements Serializable, IEnum<Integer> {

    DEFAULT(0,"正常"),
    ABNORMAL(-1,"冻结");

    private final int code;
    private final String desc;

    @JsonCreator
    public static UserStatusEnum jsonCreator(String name){
        for (UserStatusEnum value : UserStatusEnum.values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }

    @JsonValue
    public Map getResult(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", getCode());
        map.put("desc", getDesc());
        return map;
    }

    public static UserStatusEnum find(String desc ) throws IllegalArgumentException{
        for (UserStatusEnum model : UserStatusEnum.values()) {
            if (model.getDesc().equals(desc)) {
                return model;
            }
        }
        return null;
    }

    public static UserStatusEnum find(int code ) throws IllegalArgumentException{
        for (UserStatusEnum model : UserStatusEnum.values()) {
            if (model.getCode() == code) {
                return model;
            }
        }
        return null;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }
}