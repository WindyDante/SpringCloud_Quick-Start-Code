package cn.ew.order.service.impl;

import cn.ew.order.bean.Order;
import cn.ew.order.service.OrderService;
import cn.ew.product.bean.Product;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private RestTemplate restTemplate;

    @Resource   // 导入负载均衡依赖
    private LoadBalancerClient loadBalancerClient;

    @Override
    public Order createOrder(Long userId, Long productId) {
        Product productRemote = getProductRemoteLoadBalance(productId);
        // 这里是创建订单的逻辑
        Order order = new Order();
        order.setTotalAmount(new BigDecimal("10"));
        order.setId(1L);
        order.setUserId(userId);
        order.setNickName("张三");
        order.setAddress("北京市海淀区");
        // 远程查询商品列表
        order.setProductList(Arrays.asList(productRemote));
        // 总金额 = 商品数量 * 商品单价
        BigDecimal price = productRemote.getPrice();
        order.setTotalAmount(price.multiply(order.getTotalAmount()));
        return order;
    }

    @Resource
    private DiscoveryClient discoveryClient;



    private Product getProductRemoteLoadBalance(Long productId) {
        // 负载均衡:默认轮询
        ServiceInstance instance = loadBalancerClient.choose("service-product");
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求:{}",url);
        return restTemplate.getForObject(url, Product.class);
    }

    private Product getProductRemote(Long productId) {

        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        // ip
        ServiceInstance instance = instances.get(0);

        // http://localhost:9000/product/1 -> 商品远程调用地址
        // 拼接远程URL
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求:{}",url);
        return restTemplate.getForObject(url, Product.class);
    }
}
