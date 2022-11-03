package com.s6.leaguetoolserver.chat.packages.enums;

public enum OtherPakType {
    SEND_TOKEN,
    //切换大区
    CHANGE_REGION,
    //大区热度通知
    AREA_HOT,
    //基础信息
    BASE_DATA,

    ACCOUNT_ERROR,
    //历史记录
    CHAT_HISTORY;

    OtherPakType OtherPakType(String type){
        for (OtherPakType value : values()) {
            if(value.name().equals(type)){
                return value;
            }
        }
        return null;
    }

    OtherPakType() {

    }
}
