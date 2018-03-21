package com.jerry.sell.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.jerry.sell.dataobject.OrderDetail;
import com.jerry.sell.dto.OrderDTO;
import com.jerry.sell.enums.ResultEnum;
import com.jerry.sell.exception.SellException;
import com.jerry.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/3/21
 * Time: 9:39
 * Description:
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        Gson gson = new Gson();
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            orderDetails = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】错误, String={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}
