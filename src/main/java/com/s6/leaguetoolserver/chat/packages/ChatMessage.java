package com.s6.leaguetoolserver.chat.packages;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatMessage implements Serializable {
    //消息id
    private String id;
    //uid
    private String uid;
    //名字
    private String name;
    //头像
    private String avatar;
    //正文
    private String content;
    //时间
    private String time;
    //消息类型
    private String type;
    //大区
    private Region region;
    //段位
    private String rank;
    //是否是自己
    private boolean isSelf;

}
