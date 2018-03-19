package com.jerry.sell.service.impl;

import com.jerry.sell.dataobject.ProductInfo;
import com.jerry.sell.enums.ProductInfoStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl service;

    @Test
    public void findOne() {
        ProductInfo result = service.findOne("001");
        Assert.assertEquals("001", result.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = service.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0, 2);
        Page<ProductInfo> productInfoList = service.findAll(request);
        Assert.assertNotEquals(0, productInfoList.getTotalElements());
    }

    @Test
    @Transactional
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("001");
        productInfo.setProductName("鸡蛋汤");
        productInfo.setProductPrice(new BigDecimal(3.5));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("非常好喝");
        productInfo.setProductIcon("http://xxx.png");
        productInfo.setProductStatus(ProductInfoStatusEnum.UP.getCode());
        productInfo.setCategoryType(1);
        ProductInfo result = service.save(productInfo);
        Assert.assertNotEquals(null, result);
    }
}