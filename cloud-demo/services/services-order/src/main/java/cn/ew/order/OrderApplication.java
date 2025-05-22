package cn.ew.order;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableDiscoveryClient // 开启服务发现
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    // 项目启动就监听配置文件变化
    // 发生变化后拿到变化值
    // 发送邮件

    @Bean
    ApplicationRunner applicationRunner(NacosConfigManager nacosConfigManager){
        // 函数式接口直接
        return args ->{
            ConfigService configService = nacosConfigManager.getConfigService();
            // addListener(数据集Id,组Id,监听器);
            configService.addListener("service-order.properties"
                    , "DEFAULT_GROUP"
                    , new Listener() {
                        @Override
                        public Executor getExecutor() {
                            // 给出线程池大小
                            return Executors.newFixedThreadPool(4);
                        }

                        @Override
                        public void receiveConfigInfo(String configInfo) {
                            // 接收配置信息
                            System.out.println("变化的信息" + configInfo);
                            System.out.println("邮件通知...");
                        }
                    });
        };
    }

}
