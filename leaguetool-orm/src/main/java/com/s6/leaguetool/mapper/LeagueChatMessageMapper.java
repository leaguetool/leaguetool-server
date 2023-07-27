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

    List<LeagueChatMessageEntity> queryChatHistory(String region, Integer count);
}
