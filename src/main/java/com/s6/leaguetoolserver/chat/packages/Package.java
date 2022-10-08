package com.s6.leaguetoolserver.chat.packages;

import com.s6.leaguetoolserver.chat.packages.enums.MessageType;
import lombok.Data;

import java.io.Serializable;

@Data
public class Package implements Serializable {

    private MessageType type;

    private String data;
}
