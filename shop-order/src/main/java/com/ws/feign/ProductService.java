package com.ws.feign;

//Product product = restTemplate.getForObject
//        ("http://service-product/product/info/"+pid,Product.class);

import com.ws.database.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-product")//value用于指定nacos下的哪个微服务
public interface ProductService {

    //@FeignClient的value值 + @RequestMapping的value值 就是请求url
    @RequestMapping("/product/info/{pid}")//指定请求的url部分
    Product findByPid(@PathVariable Integer pid);
}
