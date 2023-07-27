package com.s6.leaguetool.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = -2071647468920117036L;
    private String name;

    private String uid;

    private String avatar;

    private boolean online;
}
