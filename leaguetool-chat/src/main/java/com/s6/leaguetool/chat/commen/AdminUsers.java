package com.s6.leaguetool.chat.commen;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import com.s6.leaguetool.model.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "admininfo")
@NacosConfigurationProperties(dataId = "leaguetool", prefix = "admininfo", type = ConfigType.YAML, autoRefreshed = true)
public class AdminUsers {

    private List<User> users;

}
