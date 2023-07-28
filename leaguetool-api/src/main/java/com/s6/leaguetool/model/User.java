package com.s6.leaguetool.model;

import lombok.*;

import java.io.Serializable;

/**
 * 用户信息
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -2071647468920117036L;
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户id
     */
    private String uid;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 在线状态
     */
    private boolean online;
}
