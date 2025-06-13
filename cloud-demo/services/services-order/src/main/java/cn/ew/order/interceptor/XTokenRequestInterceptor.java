package cn.ew.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class XTokenRequestInterceptor implements RequestInterceptor {

    /**
     * 请求拦截器
     * */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 模拟请求时添加一个自定义的请求头
        System.out.println("XTokenRequestInterceptor: Adding custom header to request");
        requestTemplate.header("X-Token", "your-token-value");
    }
}
