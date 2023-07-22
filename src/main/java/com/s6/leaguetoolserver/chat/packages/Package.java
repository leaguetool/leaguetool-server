package com.s6.leaguetoolserver.chat.packages;

import com.s6.leaguetoolserver.chat.packages.enums.MessageType;
import lombok.*;

import java.io.Serializable;

/**
 * 发包类
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Package implements Serializable {

    private static final long serialVersionUID = -5617808714813069371L;
    /**
     * 包类型
     */
    private MessageType type;

    /**
     * 包数据
     */
    private String data;
}
