package cn.ew.order.config;


import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    // 在容器中添加重试器,会自动识别
//    @Bean 测试fallback时注释掉,否则会多次重试
    Retryer retryer() {
        // 返回默认的重试器
        return new Retryer.Default();
    }

    // 注意此处的依赖,FULL日志级别会打印所有信息,一般不开启
    @Bean
    Logger.Level feignLoggerLevel() {
        // 设置Feign的日志级别为FULL
        return Logger.Level.FULL;
    }

}
