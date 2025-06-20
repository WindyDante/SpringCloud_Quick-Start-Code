package com.eastwind.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class RtGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String uri = request.getURI().toString();
        long startTime = System.currentTimeMillis();
        log.info("请求开始时间: {}", startTime);
        // ---------前置逻辑---------
        Mono<Void> filter = chain.filter(exchange)
                .doFinally((result)-> {
                    // 目标方法执行完成后,来到此处
                    long endTime = System.currentTimeMillis();
                    log.info("请求结束时间: {},耗时:{}", endTime,endTime-startTime);
                }); // 放行
        // ---------后置逻辑---------

        return filter;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
