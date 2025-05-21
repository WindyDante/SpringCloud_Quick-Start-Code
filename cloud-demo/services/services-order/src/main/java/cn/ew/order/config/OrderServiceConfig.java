package cn.ew.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderServiceConfig {

    @Bean
    @LoadBalanced  // 注解式负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
