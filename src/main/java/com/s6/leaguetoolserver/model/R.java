package com.s6.leaguetoolserver.model;

import cn.hutool.core.lang.Pair;
import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = -1153637214909232321L;
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

    /**
     * 推荐使用这种方式构建R
     * @param pair Boolean为状态，T为数据
     * @return R
     * @param <T> 泛型
     */
    public static <T> R<T> of(Pair<Boolean, T> pair) {
        Boolean status = pair.getKey();
        if (status) {
            return success(pair.getValue());
        }else{
            return err(pair.getValue().toString());
        }
    }

    public static <T> R<T> success(T data){
        return new R<>("success",0,data);
    }

    public static <T> R<T> err(String data){
        return new R<>(data,1);
    }

    public static <T extends Throwable> R fail(Integer code, String msg, T ex) {
        return new R(msg,code,ex);
    }
}
