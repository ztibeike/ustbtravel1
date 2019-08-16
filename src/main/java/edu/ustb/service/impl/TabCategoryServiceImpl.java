package edu.ustb.service.impl;

import java.util.ArrayList;
import java.util.List;

import edu.ustb.dao.TabCategoryDao;
import edu.ustb.dao.impl.TabCategoryDaoImpl;
import edu.ustb.domain.Category;
import edu.ustb.service.TabCategoryService;

public class TabCategoryServiceImpl implements TabCategoryService {
    private TabCategoryDao tcd = new TabCategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        List<Category> list = null;

        list = tcd.findAll();

        return list;
    }
}
