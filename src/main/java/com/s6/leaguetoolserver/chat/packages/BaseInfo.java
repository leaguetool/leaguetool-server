package com.s6.leaguetoolserver.chat.packages;

import com.s6.leaguetoolserver.model.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseInfo implements Serializable {
    //管理员列表
    public List<User> admins;
    //当前房间热度人员
    public List<User> hotUser;
    //其他等等...初始化的数据都可以在这里
    //热词
    public List<String> hotWords;
}
