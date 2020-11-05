package com.wang.goods.feign;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 一只永不言弃的小乌龟
 * @create 2020/11/5 3:14 下午
 */
@Component
public class FeignGoodsClientFallback implements FeignGoodsClient {
    @Override
    public Map findGood(int id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("hystrix_goods", "我被降级了");
        return map;
    }
}
