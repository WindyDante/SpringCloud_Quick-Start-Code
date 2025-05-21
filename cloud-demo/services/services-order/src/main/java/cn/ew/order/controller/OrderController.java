package cn.ew.order.controller;

import cn.ew.order.bean.Order;
import cn.ew.order.properties.OrderProperties;
import cn.ew.order.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope   // 激活远程nacos配置中心的自动刷新
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private OrderProperties orderProperties;

    @GetMapping("/config")
    public String config(){
        return "order.timeout: " + orderProperties.getTimeout() + ", order.auto-confirm: " + orderProperties.getAutoConfirm();
    }

    // 创建订单
    @GetMapping("/create")
    public Order createOrder(
            @RequestParam Long userId,
            @RequestParam Long productId
    ) {
        Order order = orderService.createOrder(userId, productId);
        return order;
    }

}
