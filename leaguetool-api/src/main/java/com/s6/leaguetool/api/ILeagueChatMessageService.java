package com.s6.leaguetool.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.s6.leaguetool.model.LeagueChatMessageEntity;

import java.util.List;

/**
 * <p>
 *  聊天消息服务类
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
public interface ILeagueChatMessageService extends IService<LeagueChatMessageEntity> {
    /**
     * 添加聊天记录
     * @param messageEntity 聊天记录实体
     */
    void addMessage(LeagueChatMessageEntity messageEntity);

    /**
     * 获取某个大区的历史记录
     * @param region 大区id
     * @param count 需要多少条
     * @return List<LeagueChatMessageEntity>
     */
    List<LeagueChatMessageEntity> getChatHistory(String region,Integer count);
}
