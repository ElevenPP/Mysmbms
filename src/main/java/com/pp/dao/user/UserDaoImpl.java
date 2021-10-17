package com.pp.dao.user;

import com.pp.dao.BaseDao;
import com.pp.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author PP
 */
public class UserDaoImpl implements UserDao{
    @Override
    public User getLoginUser(Connection connection, String userCode) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        User user = null;
        String sql = "select * from smbms_user where userCode=?";
        Object[] params = {userCode};
        if(connection != null){
            try {
                rs = BaseDao.excute(connection, sql, params, rs, preparedStatement);
                if(rs.next()){
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getInt("userRole"));
                    user.setCreatedBy(rs.getInt("createdBy"));
                    user.setCreationDate(rs.getTimestamp("creationDate"));
                    user.setModifyBy(rs.getInt("modifyBy"));
                    user.setModifyDate(rs.getTimestamp("modifyDate"));

                }
                BaseDao.closeResource(connection, preparedStatement, rs);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
                return user;

    }

    @Override
    public int updatePwd(Connection connection, int id, String password) throws SQLException {

        PreparedStatement pstm = null;
        int excute = 0;
        if(connection != null) {
            Object params[] = {id,password};
            String sql = "update smbms_user set userPassword = ? where id = ?";
            excute = BaseDao.excute(connection, sql, params, pstm);
            BaseDao.closeResource(null, pstm,null);
        }

        return excute;
    }
}
