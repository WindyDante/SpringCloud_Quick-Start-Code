package cn.ew.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    // 注意此处的依赖,FULL日志级别会打印所有信息,一般不开启
    @Bean
    Logger.Level feignLoggerLevel(){
        // 设置Feign的日志级别为FULL
        return Logger.Level.FULL;
    }

}
