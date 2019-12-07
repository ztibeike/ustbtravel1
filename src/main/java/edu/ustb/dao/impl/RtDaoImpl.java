package edu.ustb.dao.impl;

import edu.ustb.dao.RtDao;
import edu.ustb.domain.Rt;
import edu.ustb.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author 2441632735
 */
public class RtDaoImpl implements RtDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Rt> find(int cid) {
        String sql = "select * from tab_route where cid=?";
        return template.query(sql, new BeanPropertyRowMapper<Rt>(Rt.class), cid);
    }
}
