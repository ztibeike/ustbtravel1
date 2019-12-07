package edu.ustb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.ustb.dao.TabCategoryDao;
import edu.ustb.domain.Category;
import edu.ustb.domain.User;
import edu.ustb.util.JDBCUtils;

/**
 * @author 2441632735
 */
public class TabCategoryDaoImpl implements TabCategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        // TODO Auto-generated method stub
        List<Category> list = null;
        try {
            //1.定义sql
            String sql = "select * from tab_category order by cid";
            //2.执行sql
            list = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        } catch (Exception e) {

        }
        return list;
    }

}
