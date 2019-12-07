package edu.ustb.service.impl;

import edu.ustb.dao.RouteDao;
import edu.ustb.dao.impl.RouteDaoImpl;
import edu.ustb.domain.Route;
import edu.ustb.domain.RouteReq;
import edu.ustb.domain.User;
import edu.ustb.service.RouteService;
import edu.ustb.vo.PageBean;

import java.util.List;

/**
 * @author 2441632735
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao dao = new RouteDaoImpl();

    @Override
    public PageBean<Route> search(Route route, int currentPage, int pageSize) {
        if (route == null) {
            return null;
        }
        int totalCount = dao.getTotalCount(route);
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int totalPage = (totalCount - 1) / pageSize + 1;
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        PageBean<Route> pb = new PageBean<Route>();
        List<Route> list = dao.search(route, currentPage, pageSize);
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        pb.setList(list);
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findOne(Route route) {

        return dao.findOne(route);
    }

    @Override
    public boolean isFavorite(Route route) {

        return dao.isFavorite(route);
    }

    @Override
    public boolean addFavorite(Route route, User user) {
        if (route == null || user == null) {
            return false;
        }
        return dao.addFavorite(route, user) == 1;
    }

    @Override
    public boolean addFavorite(Route route) {
        if (route == null) {
            return false;
        }
        return dao.addFavorite(route) > 0;
    }

    @Override
    public PageBean<Route> search(RouteReq routeReq, int currentPage, int pageSize) {
        if (routeReq == null) {
            return null;
        }
        int totalCount = 0;
        Route route = routeReq.getRoute();
        if (route != null) {
            totalCount = dao.getTotalCount(routeReq);
        }
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int totalPage = (totalCount - 1) / pageSize + 1;
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        PageBean<Route> pb = new PageBean<Route>();
        List<Route> list = dao.search(routeReq, currentPage, pageSize);
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        pb.setList(list);
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public PageBean<Route> getByFavorite(int uid, int currentPage, int pageSize) {
        List<Route> routeList = dao.getByFavorite(uid, currentPage, pageSize);
        int totalCount = dao.getFavoriteCount(uid);
        if (currentPage <= 0) {
            currentPage = 1;
        }
        int totalPage = (totalCount - 1) / pageSize + 1;
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        PageBean<Route> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        pb.setList(routeList);
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public List<Route> find(int cid) {
        return dao.find(cid);
    }
}
