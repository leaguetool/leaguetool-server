package com.s6.leaguetool.chat.commen;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.s6.leaguetool.emoji.Emoji;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "chatsetting")
@NacosConfigurationProperties(dataId = "leaguetool", prefix = "chatsetting", type = ConfigType.YAML, autoRefreshed = true)
public class ChatSetting {

    private List<Emoji> emoji;

    private List<String> hotWords;

    private Integer historyCount;

}
