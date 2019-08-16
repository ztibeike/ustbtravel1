package edu.ustb.dao;

import java.util.List;

import edu.ustb.domain.Route;
import edu.ustb.domain.RouteReq;
import edu.ustb.domain.User;

public interface RouteDao {
    public int getTotalCount(Route route);

    public int getTotalCount(RouteReq routeReq);

    public List<Route> search(Route route, int currentPage, int pageSize);

    public Route findOne(Route route);

    public boolean isFavorite(Route route);

    public int addFavorite(Route route, User user);

    public int addFavorite(Route route);

    public List<Route> search(RouteReq routeReq, int currentPage, int pageSize);

    public List<Route> getByFavorite(int uid, int currentPage, int pageSize);

    public List<Route> find(int cid);

    public int getFavoriteCount(int uid);
}
