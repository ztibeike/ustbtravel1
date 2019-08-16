package edu.ustb.dao;

import edu.ustb.domain.Rt;

import java.util.List;

public interface RtDao {
    public List<Rt> find(int cid);
}
