package com.pp.servlet.user;

import com.alibaba.fastjson.JSONArray;
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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author PP
 * 实现servlet复用
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("savepwd")&&method!=null) {
            this.updatePwd(req,resp);
        }else if (method.equals("pwdmodify")&&method != null) {
            this.pwdModify(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    /**
     * 验证旧密码
     */
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) {
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String userCode = ((User) o).getUserCode();
        UserService userService = new UserServiceImpl();
        String password = userService.getPassword(userCode);
        String oldpassword = req.getParameter("oldpassword");

        //万能的Map:结果集
        Map<String, String> resultMap = new HashMap<String, String>();
        //session失效了,过期了
        if (o==null) {
            resultMap.put("result","sessionerror");
        }else if (StringUtils.isNullOrEmpty(oldpassword)){
            resultMap.put("result","error");
        }else {
            if (password.equals(oldpassword)) {
                resultMap.put("result","true");
            }else {
                resultMap.put("result","false");
            }
        }

        resp.setContentType("application/json");
        try {
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 修改密码
     */
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("LoginServlet--start....");
        //从session里拿到id
        Object u = req.getSession().getAttribute(Constants.USER_SESSION);
        boolean flag = false;
        String newpassword = req.getParameter("newpassword");

        if(u != null && !StringUtils.isNullOrEmpty(newpassword)){
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
        try {
            req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
