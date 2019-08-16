package edu.ustb.dao;

import java.util.List;

import edu.ustb.domain.Category;

public interface TabCategoryDao {
    List<Category> findAll();
}
