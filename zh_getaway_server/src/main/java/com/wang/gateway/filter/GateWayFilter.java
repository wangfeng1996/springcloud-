package com.wang.gateway.filter;

import jdk.nashorn.internal.ir.ThrowNode;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.TimeoutException;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author 一只永不言弃的小乌龟
 * @create 2020/11/5 4:24 下午
 * 配置全局过滤器
 */
@Component
public class GateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = request.getHeaders();
        List<String> token1 = headers.get("token");
        System.out.println(token1);
        boolean token = headers.containsKey("token");
        System.out.println(token);
        if (!token) {
            return Mono.error(new Throwable("请输入正确的Token"));
        }
        // 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
