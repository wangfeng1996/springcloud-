package com.wang.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 一只永不言弃的小乌龟
 * @create 2020/11/4 8:07 下午
 */

@RestController
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/good/{id}")
    @HystrixCommand(fallbackMethod = "findGoodBack", commandProperties = {
            //设置Hystrix的超时时间，默认1s
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    public Map findGood(@PathVariable("id") int id) {
        HashMap<String, Object> map = new HashMap<>();
//        方法一，出现异常，调用的是自己
        if (id == 1) {
            int a = 3 / 0;
        }
//        方法二，出现超时，调用的是调用方的降级处理策略
        if (id == 2) {
//            让服务调用超时，及失去连接
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        map.put("id", id);
        map.put("name", "卫龙辣条");
        map.put("price", "2元");
        map.put("title", "微辣");

        return map;
    }

    public Map findGoodBack(int id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("hystrix_order", "我出现了一些错误，被降级处理了");
        return map;
    }


}
