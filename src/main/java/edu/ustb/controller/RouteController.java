package edu.ustb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.ustb.domain.Route;
import edu.ustb.domain.RouteReq;
import edu.ustb.domain.User;
import edu.ustb.service.RouteService;
import edu.ustb.service.impl.RouteServiceImpl;
import edu.ustb.vo.PageBean;

@WebServlet("/route/*")
public class RouteController extends BaseServlet {
    private RouteService service = new RouteServiceImpl();

    public void pageQueryByRouteName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //String cid=request.getParameter("cid");
        //String rname=request.getParameter("rname");
        String cPage = request.getParameter("currentPage");
        Route route = new Route();
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(route, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*if(cid!=null && !cid.equals("")&&!cid.equals("null")){
            route.setCid(Integer.parseInt(cid));
        }
        route.setRname(rname);*/
        int pageSize = 2;
        int currentPage = 1;
        if (cPage != null && !cPage.equals("")) {
            currentPage = Integer.parseInt(cPage);
        }
        PageBean<Route> pb = service.search(route, currentPage, pageSize);
        writeValue(pb, response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> map = request.getParameterMap();
        Route route = new Route();
        User user = new User();
        try {
            BeanUtils.populate(route, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        route = service.findOne(route);
        writeValue(route, response);
    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> map = request.getParameterMap();
        Route route = new Route();
        User user = (User) request.getSession().getAttribute("user");
        try {
            BeanUtils.populate(route, map);
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        boolean flag = false;
        flag = service.isFavorite(route, user);
        writeValue(flag, response);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> map = request.getParameterMap();
        Route route = new Route();
        try {
            BeanUtils.populate(route, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Object user = request.getSession().getAttribute("user");
        boolean flag = service.addFavorite(route, (User) user);
        if (flag) {
            route = service.findOne(route);
            flag = flag && service.addFavorite(route);
        }
        writeValue(flag, response);
    }

    public void pageQueryByReq(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> map = request.getParameterMap();
        RouteReq routeReq = new RouteReq();
        Route route = new Route();
        PageBean<Route> pb = new PageBean<Route>();
        int currentPage = 1;
        int pageSize = 8;

        String cPage = request.getParameter("currentPage");
        try {
            BeanUtils.populate(route, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            BeanUtils.populate(routeReq, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        routeReq.setRoute(route);
        if (cPage != null && !cPage.equals("")) {
            currentPage = Integer.parseInt(cPage);
        }

        pb = service.search(routeReq, currentPage, pageSize);
        writeValue(pb, response);
    }

    public void getFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("uid");
        String cPage = request.getParameter("currentPage");
        int uid = 0;
        int currentPage = 1;
        int pageSize = 2;
        if (id != null && !id.equals("") && !id.equals("null")) {
            uid = Integer.parseInt(id);
        }
        if (cPage != null && !cPage.equals("") && !cPage.equals("null")) {
            currentPage = Integer.parseInt(cPage);
        }
        PageBean<Route> pb = service.getByFavorite(uid, currentPage, pageSize);
        writeValue(pb, response);
    }

    public void find(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("cid");
        int cid = 0;
        if (id != null && !id.equals("") && !id.equals("null")) {
            cid = Integer.parseInt(id);
        }
        List<Route> rtList = service.find(cid);
        writeValue(rtList, response);
    }
}
