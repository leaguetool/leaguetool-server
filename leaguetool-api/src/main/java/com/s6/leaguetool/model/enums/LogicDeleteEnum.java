package com.s6.leaguetool.model.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * enum;[DEFAULT,0,默认存在]--[DELETE,1,删除];逻辑删除
 */
@Getter
@AllArgsConstructor
public enum LogicDeleteEnum implements Serializable , IEnum<Integer> {

    DEFAULT(0,"默认存在"),
    DELETE(1,"逻辑删除");

    private final int code;
    private final String desc;

    @JsonCreator
    public static LogicDeleteEnum jsonCreator(String name){
        for (LogicDeleteEnum value : LogicDeleteEnum.values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }

    @JsonValue
    public Map<String, Object> getResult(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", getCode());
        map.put("desc", getDesc());
        return map;
    }

    public static LogicDeleteEnum find(String desc ) throws IllegalArgumentException{
        for (LogicDeleteEnum model : LogicDeleteEnum.values()) {
            if (model.getDesc().equals(desc)) {
                return model;
            }
        }
        return null;
    }

    public static LogicDeleteEnum find(int code ) throws IllegalArgumentException{
        for (LogicDeleteEnum model : LogicDeleteEnum.values()) {
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