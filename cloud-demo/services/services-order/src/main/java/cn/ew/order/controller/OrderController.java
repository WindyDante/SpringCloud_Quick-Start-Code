package cn.ew.order.controller;

import cn.ew.order.bean.Order;
import cn.ew.order.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope   // 激活远程nacos配置中心的自动刷新
@RestController
public class OrderController {


    @Resource
    private OrderService orderService;

    @Value("${order.timeout}")
    String orderTimeOut;

    @Value("${order.auto-confirm}")
    String orderAutoConfirm;

    @GetMapping("/config")
    public String config(){
        return "order.timeout: " + orderTimeOut + ", order.auto-confirm: " + orderAutoConfirm;
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
