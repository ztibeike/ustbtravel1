package edu.ustb.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ustb.domain.User;

@WebServlet("/login/*")
public class loginController extends BaseServlet {
    public void isLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user1 = new User();
        user1.setUsername("zhangsan");
        user1.setUid(1);
        request.getSession().setAttribute("user", user1);
        User user = (User) request.getSession().getAttribute("user");
        writeValue(user, response);
    }
}
