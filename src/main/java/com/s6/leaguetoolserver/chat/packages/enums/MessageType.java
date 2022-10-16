package com.s6.leaguetoolserver.chat.packages.enums;

public enum MessageType {
    //ping
    PING,
    //pong
    PONG,
    //ack
    ACK,
    //聊天
    CHAT,
    //通知
    NOTICE,
    //其他
    OTHER;

    MessageType MessageType(String type){
        for (MessageType value : values()) {
            if(value.name().equals(type)){
                return value;
            }
        }
        return null;
    }

    MessageType() {

    }
}