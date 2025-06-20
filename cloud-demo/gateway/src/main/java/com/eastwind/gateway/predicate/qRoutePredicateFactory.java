package com.eastwind.gateway.predicate;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.cloud.gateway.handler.predicate.QueryRoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class qRoutePredicateFactory extends AbstractRoutePredicateFactory<qRoutePredicateFactory.Config> {

    public qRoutePredicateFactory(){
        super(Config.class);
    }

    // 参数顺序
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("param", "value");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // localhost:8080/api/order?q=value
                ServerHttpRequest request = serverWebExchange.getRequest();
                // 获取查询参数
                String p = request.getQueryParams().getFirst(config.getParam());
                // 从配置中获取参数值，判断请求中的结果是否与参数值吻合
                if (StringUtils.hasText(p) && p.equals(config.getValue())) {
                    return true;
                }
                return false;
            }
        };
    }

    /**
     * 可以配置的参数
     * */
    @Validated
    public static class Config {
        private @NotEmpty String param;
        private @NotEmpty String value;

        public @NotEmpty String getParam() {
            return param;
        }

        public void setParam(@NotEmpty String param) {
            this.param = param;
        }

        public @NotEmpty String getValue() {
            return value;
        }

        public void setValue(@NotEmpty String value) {
            this.value = value;
        }


    }

}
