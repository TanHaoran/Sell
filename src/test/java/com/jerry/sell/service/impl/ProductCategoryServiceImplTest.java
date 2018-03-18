package com.jerry.sell.service.impl;

import com.jerry.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl service;

    @Test
    public void findOne() throws Exception {
        ProductCategory result = service.findOne(1);
        Assert.assertEquals(new Integer(1), result.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> result = service.findAll();
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findByCategoryIn() throws Exception {
        List<ProductCategory> result = service.findByCategoryIn(Arrays.asList(1, 2, 3));
        Assert.assertNotEquals(0, result.size());
    }

    @Transactional
    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory("早晨热销", 4);
        service.save(productCategory);
    }

}