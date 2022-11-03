package com.s6.leaguetoolserver.server.chatmessage.service;

import com.s6.leaguetoolserver.server.chatmessage.entity.LeagueChatMessageEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
public interface ILeagueChatMessageService extends IService<LeagueChatMessageEntity> {

    void addMessage(LeagueChatMessageEntity messageEntity);

    /**
     * 获取某个大区的历史记录
     * @param region 大区id
     * @param count 需要多少条
     * @return List<LeagueChatMessageEntity>
     */
    List<LeagueChatMessageEntity> getChatHistory(String region,Integer count);
}
