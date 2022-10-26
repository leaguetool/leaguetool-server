package com.s6.leaguetoolserver.server.chatmessage.service.impl;

import com.s6.leaguetoolserver.server.chatmessage.entity.LeagueChatMessageEntity;
import com.s6.leaguetoolserver.server.chatmessage.mapper.LeagueChatMessageMapper;
import com.s6.leaguetoolserver.server.chatmessage.service.ILeagueChatMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
}
