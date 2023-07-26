package com.s6.leaguetoolserver.chat.packages.enums;

public enum HandlerType {

    //默认处理器
    DEFAULT(null),
    //ping处理器
    PING(MessageType.PING),
    //聊天处理器
    CHAT(MessageType.CHAT),
    //其他处理器
    OTHER(MessageType.OTHER)
    ;

    HandlerType(MessageType messageType) {
    }
}
