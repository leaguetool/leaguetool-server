package com.s6.leaguetool.service;

import com.s6.leaguetool.api.ILeagueChatMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.s6.leaguetool.mapper.LeagueChatMessageMapper;
import com.s6.leaguetool.model.LeagueChatMessageEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cailong
 * @since 2022-10-26
 */
@Primary
@Service
public class LeagueChatMessageServiceImpl extends ServiceImpl<LeagueChatMessageMapper, LeagueChatMessageEntity> implements ILeagueChatMessageService {

    @Override
    public void addMessage(LeagueChatMessageEntity messageEntity) {
        this.save(messageEntity);
    }

    @Override
    public List<LeagueChatMessageEntity> getChatHistory(String region, Integer count) {
        return this.baseMapper.queryChatHistory(region, count);
    }
}
