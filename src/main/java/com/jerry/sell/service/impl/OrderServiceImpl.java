package com.jerry.sell.service.impl;

import com.jerry.sell.dataobject.OrderDetail;
import com.jerry.sell.dataobject.OrderMaster;
import com.jerry.sell.dataobject.ProductInfo;
import com.jerry.sell.dto.CartDTO;
import com.jerry.sell.dto.OrderDTO;
import com.jerry.sell.enums.OrderStatusEnum;
import com.jerry.sell.enums.PayStatusEnum;
import com.jerry.sell.enums.ResultEnum;
import com.jerry.sell.exception.SellException;
import com.jerry.sell.repository.OrderDetailRepository;
import com.jerry.sell.repository.OrderMasterRepository;
import com.jerry.sell.service.OrderService;
import com.jerry.sell.service.ProductInfoService;
import com.jerry.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/3/20
 * Time: 10:19
 * Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /**
     * 创建订单，客户端只传来详情中的商品id和商品数量
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        // 订单id
        String orderId = KeyUtil.genUniqueKey();
        // 订单总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {

            // 1、查询商品（数量、单价）
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 一个订单详情的数量
            Integer quantity = orderDetail.getProductQuantity();
            BigDecimal price = productInfo.getProductPrice();

            // 2、计算总价
            orderAmount = price.multiply(new BigDecimal(quantity)).add(orderAmount);

            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            // 订单id需要在开始就生成
            orderDetail.setOrderId(orderId);
            // 设置一个订单详情的其他属性
            BeanUtils.copyProperties(productInfo, orderDetail);

            // 3、一个订单详情入库（OrderMaster）
            orderDetailRepository.save(orderDetail);
        }

        // 4、订单入库（OrderDetail）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        // 复制信息后，因为OrderDTO里面的部分属性是空，会将orderMaster中的部分属性置空，所以要重新赋值
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        // 4、扣库存
        // 构建购物车并扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO pay(OrderDTO orderDTO) {
        return null;
    }
}
