package com.jerry.sell.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/3/19
 * Time: 13:39
 * Description:
 */
@Getter
public enum ProductInfoStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;
    private String msg;

    ProductInfoStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
