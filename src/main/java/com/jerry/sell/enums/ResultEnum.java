package com.jerry.sell.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/3/20
 * Time: 14:37
 * Description:
 */
@Getter
public enum ResultEnum {

    PARAM_ERROR(1, "参数不正确"),

    PRODUCT_NOT_EXIST(11, "商品不存在"),
    PRODUCT_STOCK_ERROR(12, "库存不足"),
    ORDER_NOT_EXIST(13, "订单不存在"),
    ORDER_DETAIL_NOT_EXIST(14, "订单详情不存在"),
    ORDER_STATUS_ERROR(15, "订单状态异常"),
    ORDER_UPDATE_FAIL(16, "订单更新失败"),
    ORDER_DETAIL_EMPTY(17, "订单详情为空"),
    ORDER_PAY_STATUS_ERROR(18, "订单支付状态异常"),
    CART_EMPTY(19, "购物车为空"),
    ORDER_BUYER_ERROR(20, "订单不属于当前买家"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
