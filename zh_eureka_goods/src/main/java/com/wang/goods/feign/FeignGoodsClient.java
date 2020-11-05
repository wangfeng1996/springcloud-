package com.wang.goods.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author 一只永不言弃的小乌龟
 * @create 2020/11/4 8:23 下午
 * value: 表示的是被调用的微服务的名称
 * fallback:表示的是降级的处理的类
 *
 */
@FeignClient(value = "ZH-EUREKA-ORDER", fallback = FeignGoodsClientFallback.class)
public interface FeignGoodsClient {
    /**
     * 调用订单中的数据
     *
     * @param id
     * @return
     */
    @RequestMapping("/order/good/{id}")
    public Map findGood(@PathVariable("id") int id);
}
