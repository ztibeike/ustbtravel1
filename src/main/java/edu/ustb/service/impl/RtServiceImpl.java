package edu.ustb.service.impl;

import edu.ustb.dao.RtDao;
import edu.ustb.dao.impl.RtDaoImpl;
import edu.ustb.domain.Rt;
import edu.ustb.service.RtService;

import java.util.List;

/**
 * @author 2441632735
 */
public class RtServiceImpl implements RtService {
    private RtDao dao = new RtDaoImpl();

    @Override
    public List<Rt> find(int cid) {
        return dao.find(cid);
    }
}
