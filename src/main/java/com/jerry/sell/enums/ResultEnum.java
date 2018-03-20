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

    PRODUCT_NOT_EXIST(11, "商品不存在"),
    PRODUCT_STOCK_ERROR(12, "库存不足"),;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
