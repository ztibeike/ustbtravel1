package edu.ustb.dao;

import java.util.List;

import edu.ustb.domain.Category;

/**
 * @author 2441632735
 */
public interface TabCategoryDao {
    List<Category> findAll();
}
