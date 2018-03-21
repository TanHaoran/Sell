package com.jerry.sell.service.impl;

import com.jerry.sell.dto.OrderDTO;
import com.jerry.sell.enums.ResultEnum;
import com.jerry.sell.exception.SellException;
import com.jerry.sell.service.BuyerService;
import com.jerry.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/3/21
 * Time: 16:42
 * Description:
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService service;

    @Override
    public OrderDTO findOrder(String openid, String orderId) {
        return checkOrderBuyer(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderBuyer(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】订单不存在, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return service.cancel(orderDTO);
    }

    /**
     * 检查是否是该买家的订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    private OrderDTO checkOrderBuyer(String openid, String orderId) {
        OrderDTO orderDTO = service.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        // 比较是否是该买家的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致, openid={}, orderId={}", openid, orderId);
            throw new SellException(ResultEnum.ORDER_BUYER_ERROR);
        }
        return orderDTO;
    }
}
