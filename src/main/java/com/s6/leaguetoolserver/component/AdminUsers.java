package com.s6.leaguetoolserver.component;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.s6.leaguetoolserver.model.User;
import lombok.Data;
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
