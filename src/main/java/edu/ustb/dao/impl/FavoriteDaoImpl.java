package edu.ustb.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import edu.ustb.dao.FavoriteDao;
import edu.ustb.domain.Route;
import edu.ustb.util.JDBCUtils;

/**
 * @author 2441632735
 */
public class FavoriteDaoImpl implements FavoriteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

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

}
