package cn.ew.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order")  // 配置绑定在nacos中,无需@RefreshScope注解就可自动刷新
@Data
public class OrderProperties {

    String timeout;

    String autoConfirm;
}
