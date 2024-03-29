package com.s6.leaguetool.config.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 全局异常
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 错误码
     */
    private int code;

    public GlobalException() {
        this.code = 500;
    }

    public GlobalException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }

    public GlobalException(String msg, int code) {
//        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public GlobalException(String msg, int code, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
        this.code = code;
    }

    public GlobalException(String msg) {
        this.code = 500;
        this.msg = msg;
    }

    public static String getStackTrace(Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        StringWriter sw = new StringWriter();

        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

    @Override
    public String toString() {
        return getStackTrace(getCause());
    }
}