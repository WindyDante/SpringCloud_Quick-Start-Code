package cn.ew.order.service.impl;

import cn.ew.order.bean.Order;
import cn.ew.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(Long userId, Long productId) {
        // 这里是创建订单的逻辑
        Order order = new Order();
        order.setId(1L);
        order.setUserId(userId);
        order.setNickName("张三");
        order.setAddress("北京市海淀区");
        // todo 远程查询商品列表
        order.setProductList(null);
        // todo 总金额
        order.setTotalAmount(new BigDecimal("0"));
        return order;
    }
}
