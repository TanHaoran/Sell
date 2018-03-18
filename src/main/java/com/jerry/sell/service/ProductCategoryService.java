package com.jerry.sell.service;

import com.jerry.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/3/18
 * Time: 19:06
 * Description:
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryIn(List<Integer> categoryList);

    ProductCategory save(ProductCategory productCategory);
}
