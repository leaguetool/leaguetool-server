package com.s6.leaguetool.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.s6.leaguetool.model.LeagueChatMessageEntity;

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
    /**
     * 查询聊天记录
     * @param region 大区
     * @param count 数量
     * @return 聊天记录列表
     */
    List<LeagueChatMessageEntity> queryChatHistory(String region, Integer count);
}
