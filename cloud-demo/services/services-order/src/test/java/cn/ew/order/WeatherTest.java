package cn.ew.order;

import cn.ew.order.feign.WeatherFeignClient;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherTest {

    @Resource
    private WeatherFeignClient weatherFeignClient;

    @Test
    void test() {
        String res = weatherFeignClient.getWeather("Test","token","id");
        System.out.println(res);
    }

}
