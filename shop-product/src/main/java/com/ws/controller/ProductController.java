package com.ws.controller;

import com.alibaba.fastjson.JSON;
import com.ws.database.Product;
import com.ws.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;
    //商品查询
    @RequestMapping("/info/{pid}")
    public Product getProductInfo(@PathVariable("pid") Integer pid){
        log.info("start-查询{}商品信息",pid);
        Product product = productService.findByPid(pid);
        log.info("end-查询商品信息,内容为{}", JSON.toJSONString(product));
        return product;
    }
}
