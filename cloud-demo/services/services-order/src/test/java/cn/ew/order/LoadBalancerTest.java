package cn.ew.order;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
public class LoadBalancerTest {

    @Resource
    LoadBalancerClient client;

    @Test
    void test(){
        ServiceInstance instance = client.choose("service-product");
        System.out.println(instance.getHost() +instance.getPort());
        ServiceInstance instance2 = client.choose("service-product");
        System.out.println(instance2.getHost() +instance2.getPort());
        ServiceInstance instance3 = client.choose("service-product");
        System.out.println(instance3.getHost() +instance3.getPort());
        ServiceInstance instance4 = client.choose("service-product");
        System.out.println(instance4.getHost() +instance4.getPort());
    }

}
