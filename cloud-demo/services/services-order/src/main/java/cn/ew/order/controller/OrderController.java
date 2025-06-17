package cn.ew.order.controller;

import cn.ew.order.bean.Order;
import cn.ew.order.properties.OrderProperties;
import cn.ew.order.service.OrderService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import jakarta.annotation.Resource;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope   // 激活远程nacos配置中心的自动刷新
public class OrderController {


    @Resource
    private OrderService orderService;

    @Resource
    private OrderProperties orderProperties;

    @GetMapping("/config")
    public String config(){
        return "order.timeout: " + orderProperties.getTimeout() + ", order.auto-confirm: " + orderProperties.getAutoConfirm()
                + ",order.db.url: " + orderProperties.getDbUrl();
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

    // 秒杀创建订单(测试链路流控规则)
    @GetMapping("/seckill")
    public Order seckill(
            @RequestParam Long userId,
            @RequestParam Long productId
    ) {
        // 在Sentinel 中配置链路流控规则的高级选项，针对链路的入口资源设置，如果是seckill接口，就进行限制
        // 当然很多人会疑问为什么不直接对seckill接口进行限流，而是对createOrder方法进行限流？
        // 因为seckill中可能还会调其他服务，如果直接对seckill接口进行限流，那么其他服务也会一起被限制
        // 只针对从秒杀创建订单进入的订单服务限流，就是单一的方法路线设置了
        Order order = orderService.createOrder(userId, productId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

}
