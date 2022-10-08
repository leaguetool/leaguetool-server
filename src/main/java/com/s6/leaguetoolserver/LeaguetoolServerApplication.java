package com.s6.leaguetoolserver;

import com.s6.leaguetoolserver.chat.LeagueWebsocketStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeaguetoolServerApplication {

    public static void main(String[] args) {
        //启动http server
        SpringApplication.run(LeaguetoolServerApplication.class, args);
        //启动websocket
        LeagueWebsocketStarter.main(args);
    }

}
