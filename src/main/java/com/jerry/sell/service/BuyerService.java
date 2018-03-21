package com.jerry.sell.service;

import com.jerry.sell.dto.OrderDTO;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/3/21
 * Time: 16:40
 * Description:
 */
public interface BuyerService {

    /**
     * 查询一个订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrder(String openid, String orderId);

    /**
     * 取消订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid, String orderId);
}
