package com.s6.leaguetoolserver.chat.packages;

import lombok.Data;

import java.io.Serializable;
@Data
public class Region implements Serializable {
    //大区名字
    private String name;
    //大区id
    private Integer id;
}
