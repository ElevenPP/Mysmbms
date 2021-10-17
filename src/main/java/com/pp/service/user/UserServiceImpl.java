package com.pp.service.user;

import com.pp.dao.BaseDao;
import com.pp.dao.user.UserDao;
import com.pp.dao.user.UserDaoImpl;
import com.pp.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    //业务层都会调用Dao层
    private UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection, userCode);

            //添加对密码的匹配
//            if(!user.getUserPassword().equals(password)){
//                user = null;
//            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //资源关闭
            BaseDao.closeResource(connection,null,null);
        }

        return user;
    }

    @Override
    public boolean updatePwd(int id, String password) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if(userDao.updatePwd(connection, id, password)>0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return flag;
    }

    @Test
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.login("test", "111");
        System.out.println(user.getUserPassword());

    }
}
