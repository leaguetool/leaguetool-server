package com.s6.leaguetoolserver.chat.packages.enums;

public enum OtherPakType {
    SEND_TOKEN,
    //切换大区
    CHANGE_REGION
    ;

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
