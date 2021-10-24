package com.pp.service.user;

import com.pp.pojo.User;

public interface UserService {
    /**
     * 用户登录
     */
    public User login(String userCode,String password);

    /**
     * 根据用户id修改密码
     */
    public boolean updatePwd(int id, String password);

    /**
     * 获得用户当前密码
     */
    public String getPassword(String userCode);

    /**
     * 查询记录数
     */
    public int getUserCount(String userName,int userRole);

}
