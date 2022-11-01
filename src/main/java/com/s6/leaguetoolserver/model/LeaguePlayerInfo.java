package com.s6.leaguetoolserver.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LeaguePlayerInfo implements Serializable {
    private static final long serialVersionUID = -2021678012147517108L;

    private int exp;
    private int iconId;
    private int level;
    private String msg;
    private String name;
    private int retCode;
}
