package com.s6.leaguetoolserver.enums;

import lombok.Getter;

/**
 * HttpResult
 */
@Getter
public enum HttpResultEnum {

    LOGIN_SUCCESS(200, "登录成功"),
    LOGOUT_SUCCESS(200, "退出成功"),

    UNKNOWN_ERR(-1, "未知错误"),
    UNEXIST(2, "数据不存在！"),
    SUCCESS(200, "成功！"),
    SUCCESSMSG(1, "成功！+Messages"),
    FAIL(-2, "失败！"),
    PARAM_ERR(1004, "参数错误"),
    REPEAT(1005, "数据重复"),
    VALID_CODE_ERR(10, "验证码错误"),
    POWER_ERR(503, "未授权,无权限操作"),
    EMPTY_EXCEPTION(5000, "空指针异常"),
    RUN_EXCEPTION(5001, "运行时异常"),
    CONVERT_EXCEPTION(5002, "类型转换异常"),
    IO_EXCEPTION(5003, "IO异常"),
    UNKNOEWN_EXCEPTION(5004, "未知方法异常"),
    OVERSTEP_EXCEPTION(5005, "数组越界异常"),
    STACK_OVERFLOW_EXCEPTION(5006, "栈溢出"),
    DIVISOR_ZERO_EXCEPTION(5007, "除数不能为0"),
    ERR_400_EXCEPTION(400, "400错误"),
    ERR_401_EXCEPTION(401, "token认证失败"),
    ERR_402_EXCEPTION(402, "请登录"),
    ERR_403_EXCEPTION(403, "没有访问权限"),
    ERR_404_EXCEPTION(404, "404错误,系统未找到"),
    ERR_405_EXCEPTION(405, "405错误,请使用标准的请求方式"),
    ERR_406_EXCEPTION(406, "406错误"),
    ERR_500_EXCEPTION(500, "500错误"),


    ERR_USER_NOT_LOGIN(401, "请登录"),
    ERR_USER_TOKEN_INVALID(401, "token认证失败"),
    ERR_USER_TOKEN_EXPIRE(401, "token已经过期"),
    ERR_USER_CACHE_TOKEN_EXPIRE(401, "token已经过期"),
    ERR_USER_NOT_FIND_EXCEPTION(445, "要操作的账号不存在"),

    ERR_USER_ACCOUNT_INVALID(1023, "对方账号已被冻结"),
    ERR_USER_ACK_SYSTEMMSG_MSGTYPE_NOT_FOUND(1024, "消息类型不正确"),

    ERR_DYNAMIC_NOT_FIND_EXCEPTION(3362, "要操作的动态不存在"),
    USER_EXIST(3363, "用户名已经存在"),
    PHONE_EXIST(3364, "手机号已经存在"),
    NO_SUCH_PERSON(3365,"查无此人!")
    ;


    private final int code;
    private final String message;

    public int getInt() {
        return code;
    }

    public Byte getBtyeCode() {
        return Integer.valueOf(code).byteValue();
    }

    HttpResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
