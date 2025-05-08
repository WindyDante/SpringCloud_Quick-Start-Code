package cn.ew.order.service;

import cn.ew.order.bean.Order;

public interface OrderService {
    Order createOrder(Long userId, Long productId);
}
