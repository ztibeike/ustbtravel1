package edu.ustb.service;

import java.util.List;

import edu.ustb.domain.Route;
import edu.ustb.domain.RouteReq;
import edu.ustb.domain.User;
import edu.ustb.vo.PageBean;

/**
 * @author 2441632735
 */
public interface RouteService {
    PageBean<Route> search(Route route, int currentPage, int pageSize);

    Route findOne(Route route);

    boolean isFavorite(Route route);

    boolean addFavorite(Route route, User user);

    boolean addFavorite(Route route);

    PageBean<Route> search(RouteReq routeReq, int currentPage, int pageSize);

    PageBean<Route> getByFavorite(int uid, int currentPage, int pageSize);

    public List<Route> find(int cid);
}
