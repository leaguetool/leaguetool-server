package com.s6.leaguetoolserver.server.chatmessage.mapper;

import com.s6.leaguetoolserver.server.chatmessage.entity.LeagueChatMessageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
public interface LeagueChatMessageMapper extends BaseMapper<LeagueChatMessageEntity> {

    List<LeagueChatMessageEntity> queryChatHistory(String region, Integer count);
}
