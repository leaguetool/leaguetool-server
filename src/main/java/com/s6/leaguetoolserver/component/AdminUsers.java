package com.s6.leaguetoolserver.component;

import com.s6.leaguetoolserver.model.User;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "admininfo")
public class AdminUsers {

    private List<User> users;
}
