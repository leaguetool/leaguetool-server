package com.s6.leaguetoolserver.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {

    private String msg;

    private int code;

    private T data;

    R(String msg, int code,T data){
        this.msg = msg;
        this.code = code;
        this.data = data;
    }
    R(String msg, int code){
        this.msg = msg;
        this.code = code;
    }

    public static R success(Object data){
        return new R("success",0,data);
    }

    public static R err(String data){
        return new R(data,1);
    }
}
