package com.s6.leaguetoolserver.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "chatsetting")
public class ChatSetting {

    private List<Emoji> emoji;

    @Data
   static class Emoji {
       private String title;

       private String text;

       private List<Emoji> data;
    }
}
