package com.jerry.sell.vo;

import lombok.Data;


/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/3/19
 * Time: 14:02
 * Description:
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码，0：成功，其它：失败
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;
}
