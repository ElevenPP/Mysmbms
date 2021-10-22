package com.pp.servlet.user;

import com.pp.dao.user.UserDaoImpl;
import com.pp.pojo.User;
import com.pp.service.user.UserService;
import com.pp.service.user.UserServiceImpl;
import com.pp.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    //Servlet:控制层，调用业务代码


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet--start....");

        //获取用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);

        if(user != null) {
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            resp.sendRedirect("/jsp/frame.jsp");
        }else {
            req.setAttribute("error", "用户名或密码有误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
