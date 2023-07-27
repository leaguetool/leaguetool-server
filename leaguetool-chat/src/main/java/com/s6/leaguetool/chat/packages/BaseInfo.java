package com.s6.leaguetool.chat.packages;

import lombok.Data;
import com.s6.leaguetool.model.User;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseInfo implements Serializable {
    private static final long serialVersionUID = -7383711006107783262L;
    //管理员列表
    public List<User> admins;
    //当前房间热度人员
    public List<User> hotUser;
    //其他等等...初始化的数据都可以在这里
    //热词
    public List<String> hotWords;
}
