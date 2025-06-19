package cn.ew.order.controller;

import cn.ew.order.bean.Order;
import cn.ew.order.properties.OrderProperties;
import cn.ew.order.service.OrderService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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
    @SentinelResource(value = "seckill-order",fallback = "seckillFallback")
    public Order seckill(
            // 通常是根据参数来进行流控规则的,如果不携带某个参数,则不进行流控
//            @RequestParam(defaultValue = "888") Long userId,
            @RequestParam(required = false) Long userId,
            @RequestParam(defaultValue = "666") Long productId
    ) {
        // 在Sentinel 中配置链路流控规则的高级选项，针对链路的入口资源设置，如果是seckill接口，就进行限制
        // 当然很多人会疑问为什么不直接对seckill接口进行限流，而是对createOrder方法进行限流？
        // 因为seckill中可能还会调其他服务，如果直接对seckill接口进行限流，那么其他服务也会一起被限制
        // 只针对从秒杀创建订单进入的订单服务限流，就是单一的方法路线设置了
        Order order = orderService.createOrder(userId, productId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    public Order seckillFallback(
            Long userId,
            Long productId,
            BlockException ex
    ) {
        // 在Sentinel 中配置链路流控规则的高级选项，针对链路的入口资源设置，如果是seckill接口，就进行限制
        // 当然很多人会疑问为什么不直接对seckill接口进行限流，而是对createOrder方法进行限流？
        // 因为seckill中可能还会调其他服务，如果直接对seckill接口进行限流，那么其他服务也会一起被限制
        // 只针对从秒杀创建订单进入的订单服务限流，就是单一的方法路线设置了
        Order order = new Order();
        order.setAddress("限流了，秒杀失败");
        return order;
    }

    // 测试Sentinel的关联流控规则
    @GetMapping("/writeDb")
    public String writeDb() {
        return "写入数据库成功";
    }

    // 测试Sentinel的关联流控规则
    // 为readDb设置关联流控规则
    // 设置qps为1,关联writeDb，当writeDb刷新特快的时候，访问readDb的qps会被限制
    // 因为readDb和writeDb是关联的，readDb的qps会被writeDb的qps限制
    @GetMapping("/readDb")
    public String readDb() {
        return "读取数据库成功";
    }

}
