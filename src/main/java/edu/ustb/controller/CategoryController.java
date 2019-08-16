package edu.ustb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ustb.domain.Category;
import edu.ustb.service.TabCategoryService;
import edu.ustb.service.impl.TabCategoryServiceImpl;

@WebServlet("/category/*")
public class CategoryController extends BaseServlet {
    private TabCategoryService tcs = new TabCategoryServiceImpl();


    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Category> list = new ArrayList();
        list = tcs.findAll();
        writeValue(list, response);
    }
}