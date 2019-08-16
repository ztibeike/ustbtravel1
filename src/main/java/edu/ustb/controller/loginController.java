package edu.ustb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ustb.domain.User;
import edu.ustb.service.UserService;
import edu.ustb.service.impl.UserServiceImpl;
import edu.ustb.vo.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login/*")
public class loginController extends BaseServlet {
    private UserService service = new UserServiceImpl();

    public void isLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user1 = new User();
        user1.setUsername("zhangsan");
        user1.setUid(1);
        request.getSession().setAttribute("user", user1);
        User user = (User) request.getSession().getAttribute("user");
//        if(user!=null&&!user.equals("")&&!user.equals("null")){
//            result=true;
//        }else{
//            result=false;
//        }
//        ResultInfo info=new ResultInfo();
//        info.setFlag(result);
//        if(result=true){
//            info.setData(user);
//        }
//        writeValue(info,response);
        writeValue(user, response);

    }

}
