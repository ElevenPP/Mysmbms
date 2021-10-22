package com.pp.service.user;

import com.pp.dao.BaseDao;
import com.pp.dao.user.UserDao;
import com.pp.dao.user.UserDaoImpl;
import com.pp.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author PP
 */
public class UserServiceImpl implements UserService{

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
            if(!user.getUserPassword().equals(password)){
                user = null;
            }
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
        System.out.println("进入UserServiceImpl_updatePwd方法...");
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

    @Override
    public String getPassword(String userCode) {
        Connection connection = null;
        String password = "";
        try {
            connection = BaseDao.getConnection();
            password = userDao.getPassword(connection, userCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResource(connection,null,null);
        }
        return password;
    }

    @Test
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.login("test", "111");
        System.out.println(user.getUserPassword());

    }
}
