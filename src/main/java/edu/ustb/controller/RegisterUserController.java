package edu.ustb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ustb.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ustb.domain.User;
import edu.ustb.service.UserService;
import edu.ustb.vo.ResultInfo;


@WebServlet("/register.do")
public class RegisterUserController extends HttpServlet {
    private UserService service = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //获取所有请求参数
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            //封装对象
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service添加会员
        boolean flag = service.register(user);
        //封装响应信息
        ResultInfo info = new ResultInfo();
        if (flag) {
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        //将响应信息对象转变json字符串
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(info);
        //响应浏览器
        response.getWriter().write(result);

    }

}
