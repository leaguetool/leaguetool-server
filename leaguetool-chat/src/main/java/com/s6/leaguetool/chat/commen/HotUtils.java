package com.s6.leaguetool.chat.commen;

/**
 * 热度工具类
 */
public class HotUtils {

    /**
     * 传入人数，获取热度
     * @param count 人数
     * @return 热度
     */
    public static int getHot(int count){
        return count * 1024 - 996;
    }
}
