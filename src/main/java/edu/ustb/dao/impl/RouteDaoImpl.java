package edu.ustb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.ustb.dao.RouteDao;
import edu.ustb.domain.Category;
import edu.ustb.domain.Favorite;
import edu.ustb.domain.Route;
import edu.ustb.domain.RouteImg;
import edu.ustb.domain.RouteReq;
import edu.ustb.domain.Seller;
import edu.ustb.domain.User;
import edu.ustb.util.JDBCUtils;

public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int getTotalCount(Route route) {
        int totalCount = 0;
        String sql = "select count(*) from tab_route where 1=1";
        if (route.getCid() > 0) {
            sql += " and cid = " + route.getCid();
        }
        if (route.getRname() != null && !route.getRname().equals("") && !route.getRname().equals("null")) {
            sql += " and rname like '%" + route.getRname() + "%'";
        }
        try {
            totalCount = template.queryForObject(sql, Integer.class);
        } catch (Exception e) {

        }

        return totalCount;
    }

    @Override
    public List<Route> search(Route route, int currentPage, int pageSize) {
        List<Route> list = null;
        String sql = "select * from tab_route where 1=1";
        if (route.getCid() > 0) {
            sql += " and cid = " + route.getCid();
        }
        if (route.getRname() != null && !route.getRname().equals("") && !route.getRname().equals("null")) {
            sql += " and rname like '%" + route.getRname() + "%'";
        }
        sql += " order by rid limit ?,?";
        try {
            list = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), (currentPage - 1) * pageSize, pageSize);
        } catch (Exception e) {

        }

        return list;
    }

    @Override
    public Route findOne(Route route) {
        Route r = null;
        Seller seller = null;
        Category category = null;
        List<RouteImg> routeImgList = null;
        String sql = "select * from tab_route where 1=1";
        if (route.getRid() > 0) {
            sql += " and rid=" + route.getRid();
        }
        try {
            r = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class));
        } catch (Exception e) {

        }

        sql = "select * from tab_seller where 1=1";
        if (r != null) {
            if (r.getSid() > 0) {
                sql += " and sid=" + r.getSid();
            }
        }
        try {
            seller = template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class));
        } catch (Exception e) {

        }

        sql = "select * from tab_category where 1=1";
        if (r != null) {
            if (r.getCid() > 0) {
                sql += " and cid=" + r.getCid();
            }
        }
        try {
            category = template.queryForObject(sql, new BeanPropertyRowMapper<Category>(Category.class));
        } catch (Exception e) {

        }

        sql = "select * from tab_route_img where 1=1";
        if (r != null) {
            if (r.getRid() > 0) {
                sql += " and rid=" + r.getRid();
            }
        }
        try {
            routeImgList = template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class));
        } catch (Exception e) {

        }

        if (r != null) {
            r.setCategory(category);
            r.setSeller(seller);
            r.setRouteImgList(routeImgList);
        }

        return r;
    }

    @Override
    public boolean isFavorite(Route route) {
        boolean flag = false;
        String sql = "select count(*) from tab_favorite where 1=1";
        if (route.getRid() > 0) {
            sql += " and rid=" + route.getRid();
        }
        try {
            flag = template.queryForObject(sql, Integer.class) > 0;
        } catch (Exception e) {

        }
        return flag;
    }

    @Override
    public int addFavorite(Route route, User user) {
        int result = 0;
        String sql = "insert into tab_favorite(rid,date,uid) values(?,now(),?)";
        try {

            result = template.update(sql, route.getRid(), user.getUid());
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public int addFavorite(Route route) {
        int count = 0;
        String sql = "update tab_route set count=? where 1=1";
        if (route.getRid() > 0) {
            sql += " and rid=" + route.getRid();
        }
        try {

            if (route.getCount() >= 0) {
                count = template.update(sql, (route.getCount() + 1));
            }
        } catch (Exception e) {

        }
        return count;
    }

    @Override
    public List<Route> search(RouteReq routeReq, int currentPage, int pageSize) {
        List<Route> list = null;
        String sql = "select * from tab_route where 1=1";
        if (routeReq.getRoute() != null) {
            Route route = routeReq.getRoute();
        	/*if (route.getCid() > 0) {
            sql += " and cid = " + route.getCid();
        	}*/
            if (route.getRname() != null && !route.getRname().equals("") && !route.getRname().equals("null")) {
                sql += " and rname like '%" + route.getRname() + "%'";
            }
            if (routeReq.getMinPrice() > 0) {
                sql += " and price>=" + routeReq.getMinPrice();
            }
            if (routeReq.getMaxPrice() > 0 && routeReq.getMaxPrice() > routeReq.getMinPrice()) {
                sql += " and price<=" + routeReq.getMaxPrice();
            }
        }

        sql += " order by count desc,price asc limit ?,?";
        try {
            list = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), (currentPage - 1) * pageSize, pageSize);
        } catch (Exception e) {

        }

        return list;
    }

    @Override
    public List<Route> getByFavorite(int uid, int currentPage, int pageSize) {
        String sql1 = "select * from tab_favorite where uid=? order by rid limit ?,?";
        String sql2 = "select * from tab_route where rid=?";
        List<Favorite> favoriteList = template.query(sql1, new BeanPropertyRowMapper<Favorite>(Favorite.class), uid, (currentPage - 1) * pageSize, pageSize);
        List<Route> routeList = new ArrayList<>();
        for (Favorite fav : favoriteList) {
            Route route = null;
            try {
                route = template.queryForObject(sql2, new BeanPropertyRowMapper<Route>(Route.class), fav.getRid());
            } catch (Exception e) {

            }
            if (route != null) {
                routeList.add(route);
            }
        }
        return routeList;
    }

    @Override
    public List<Route> find(int cid) {
        String sql = "select * from tab_route where cid=?";
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), cid);
    }

    @Override
    public int getFavoriteCount(int uid) {
        String sql="select * from tab_favorite where uid = ?";
        List<Favorite> fav=template.query(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),uid);
        return fav.size();
    }

    @Override
    public int getTotalCount(RouteReq routeReq) {
        int totalCount = 0;
        Route route = routeReq.getRoute();
        String sql = "select count(*) from tab_route where 1=1";
        if (route != null) {
            if (route.getCid() > 0) {
                sql += " and cid = " + route.getCid();
            }
            if (route.getRname() != null && !route.getRname().equals("") && !route.getRname().equals("null")) {
                sql += " and rname like '%" + route.getRname() + "%'";
            }
            if (routeReq.getMinPrice() > 0) {
                sql += " and price>=" + routeReq.getMinPrice();
            }
            if (routeReq.getMaxPrice() > 0 && routeReq.getMaxPrice() > routeReq.getMinPrice()) {
                sql += " and price<=" + routeReq.getMaxPrice();
            }
        }

        try {
            totalCount = template.queryForObject(sql, Integer.class);
        } catch (Exception e) {

        }

        return totalCount;
    }
}
