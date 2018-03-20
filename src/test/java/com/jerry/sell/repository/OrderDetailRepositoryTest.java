package com.jerry.sell.repository;

import com.jerry.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveText() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("003");
        orderDetail.setOrderId("002");
        orderDetail.setProductIcon("http://xxx.png");
        orderDetail.setProductId("003");
        orderDetail.setProductName("雪碧");
        orderDetail.setProductPrice(new BigDecimal(10));
        orderDetail.setProductQuantity(2);
        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrOrderId() {
        List<OrderDetail> result = repository.findByOrOrderId("001");
        Assert.assertNotEquals(true, result.size() > 0);
    }
}
