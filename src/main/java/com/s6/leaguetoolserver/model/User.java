package com.s6.leaguetoolserver.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String name;

    private String uid;

    private String avatar;

    private boolean online;
}
