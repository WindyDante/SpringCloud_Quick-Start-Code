package cn.ew.product;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest
public class DiscoveryTest {

    @Resource
    private DiscoveryClient discoveryClient;

    @Test
    void discoveryClientTest(){
        // 获取服务列表
        for (String service : discoveryClient.getServices()) {
            // 获取服务实例(根据服务名称)
            for (ServiceInstance instance : discoveryClient.getInstances(service)) {
                System.out.println("Ip: " + instance.getHost());
                System.out.println("Port: " + instance.getPort());
            }
        }
        
    }

}
