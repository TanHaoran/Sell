package com.jerry.sell.repository;

import com.jerry.sell.dataobject.OrderMaster;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String BUYER_OPENID = "abc";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("002");
        orderMaster.setBuyerName("谭浩然");
        orderMaster.setBuyerPhone("13002909620");
        orderMaster.setBuyerAddress("西安");
        orderMaster.setBuyerOpenid(BUYER_OPENID);
        orderMaster.setOrderAmount(new BigDecimal(225));
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderMaster> result = repository.findByBuyerOpenid(BUYER_OPENID, request);
        Assert.assertNotEquals(0, result.getSize());
    }
}