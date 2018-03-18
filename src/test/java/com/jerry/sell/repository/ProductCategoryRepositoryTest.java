package com.jerry.sell.repository;

import com.jerry.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOne() {
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("孩子最爱", 3);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByCategoryTypeInText() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0, result.size());
    }

}