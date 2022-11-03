package com.s6.leaguetoolserver.chat.packages;

import com.s6.leaguetoolserver.server.chatmessage.entity.LeagueChatMessageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tio.core.Tio;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = -7818101703296297804L;
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

    public ChatMessage(LeagueChatMessageEntity leagueChatMessageEntity){
        this.id = leagueChatMessageEntity.getId();
        this.uid = leagueChatMessageEntity.getUid();
        this.name = leagueChatMessageEntity.getDisplayName();
        this.avatar = leagueChatMessageEntity.getAvatar();
        this.content = leagueChatMessageEntity.getContent();
        this.time = Long.toString(leagueChatMessageEntity.getCreateTime().getTime());
        this.type = leagueChatMessageEntity.getMsgType();
        this.rank = leagueChatMessageEntity.getRank();
        this.region = new Region(leagueChatMessageEntity.getUserAreaName(), leagueChatMessageEntity.getUserArea());
    }

}
