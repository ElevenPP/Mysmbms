package com.pp.dao.user;

import com.mysql.jdbc.StringUtils;
import com.pp.dao.BaseDao;
import com.pp.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        System.out.println("??????UserDaoImpl_updatePwd??????...");
        PreparedStatement pstm = null;
        int excute = 0;
        if(connection != null) {
            Object params[] = {password,id};
            String sql = "UPDATE smbms_user SET userPassword = ? where id = ?";

            System.out.println("????????????sql");
            System.out.println("update smbms_user set userPassword = "+password+" where id = "+id);
            excute = BaseDao.excute(connection, sql, params, pstm);
            BaseDao.closeResource(null, pstm,null);
        }

        return excute;
    }

    @Override
    public String getPassword(Connection connection, String userCode) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String userPassword = "";
        String sql = "select userPassword from smbms_user where userCode = ?";
        Object[] params = {userCode};
        if (connection != null) {
            rs = BaseDao.excute(connection, sql, params, rs, preparedStatement);
            /*
            * ??????rs.getString();??????????????????rs.next();
            * ?????????ResultSet????????????SQL?????????????????????????????????????????????????????????????????????
            * ???????????????next()?????????????????????????????????????????????????????????????????????
            * ?????????????????????next()?????????????????????????????????????????????????????????
            * ??????????????????next()????????????????????????????????????
            * ???ResultSe????????????t??????Statement????????????????????????????????????????????????
            * */
            if (rs.next()) {
                userPassword = rs.getString("userPassword");
            }
            System.out.println(userPassword);
        }
        BaseDao.closeResource(connection,preparedStatement,rs);
        return userPassword;
    }

    /**
     * ??????????????????
     *
     * @param connection
     * @param userName
     * @param userRole
     */
    @Override
    public int getUserCount(Connection connection, String userName, int userRole) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int count = 0;
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u, smbms_role r where u.userRole = r.id");

            ArrayList<Object> list = new ArrayList<Object>();

            if (!StringUtils.isNullOrEmpty(userName)) {
                sql.append(" and u.userName like ?");
                list.add("%"+userName+"%");
            }

            if (userRole > 0) {
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }

            Object[] params = list.toArray();

            System.out.println("UserDaoImpl->getUserCount:"+sql.toString());

            rs = BaseDao.excute(connection, sql.toString(), params, rs, preparedStatement);
            if (rs.next()) {
                count = rs.getInt("count");
            }

            BaseDao.closeResource(connection,preparedStatement,rs);
        }
        return count;
    }

}
