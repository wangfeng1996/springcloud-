package com.wang.goods.controller;

import com.wang.goods.feign.FeignGoodsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 一只永不言弃的小乌龟
 * @create 2020/11/4 8:05 下午
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private FeignGoodsClient feignGoodsClient;

    @RequestMapping("/findGood/{id}")
    public Map findGood(@PathVariable("id") int id) {
        Map good = feignGoodsClient.findGood(id);
        return good;
    }
}
