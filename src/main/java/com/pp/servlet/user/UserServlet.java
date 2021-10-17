package com.pp.servlet.user;

import com.mysql.jdbc.StringUtils;
import com.pp.pojo.User;
import com.pp.service.user.UserService;
import com.pp.service.user.UserServiceImpl;
import com.pp.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//实现servlet复用
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session里拿到id
        Object u = req.getSession().getAttribute(Constants.USER_SESSION);
        boolean flag = false;
        String newpassword = req.getParameter("newpassword");

        if(u != null && StringUtils.isNullOrEmpty(newpassword)){
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User) u).getId(), newpassword);
            if(flag) {
                req.setAttribute("message","修改密码成功，请退出使用新密码登录");
                //密码修改成功，移除session
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else {
                req.setAttribute("message","修改密码失败");
            }
        }else {
            req.setAttribute("message","新密码有问题");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
