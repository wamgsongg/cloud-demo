package com.ws.controller;

import com.alibaba.fastjson.JSON;
import com.ws.database.Order;
import com.ws.database.Product;
import com.ws.feign.ProductService;
import com.ws.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private DiscoveryClient discoveryClient;

    //下单--feign
    @RequestMapping("/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("{}号商品下单请求",pid);

        Product product = productService.findByPid(pid);


        log.info("查询到{}号商品信息，内容是{}",pid, JSON.toJSONString(product));
        //下单

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);



        orderService.createOrder(order);
        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
        return order;
    }

//    //下单--ribbon(springcloud组件)负载均衡
//    @RequestMapping("/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("{}号商品下单请求",pid);
////        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
////        //随机选择
////        int index = new Random().nextInt(instances.size());
////        ServiceInstance instance = instances.get(index);
//        //q
//        //代码可读性不好、编程风格不统一
//        //a
//        //feign 集成ribbon nacos兼容feign 所以在nacos下使用feign默认就实现了负载均衡
//        Product product = restTemplate.getForObject("http://service-product/product/info/"+pid,Product.class);
//        log.info("查询到{}号商品信息，内容是{}",pid, JSON.toJSONString(product));
//        //下单
//
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(pid);
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//
//
//
//        orderService.createOrder(order);
//        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
//        return order;
//    }

//    //下单--nacos
//    @RequestMapping("/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("{}号商品下单请求",pid);
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//
//        ServiceInstance instance = instances.get(0);
//
//
//        Product product = restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/product/info/"+pid,Product.class);
//        log.info("查询到{}号商品信息，内容是{}",pid, JSON.toJSONString(product));
//        //下单
//
//        Order order = new Order();
//        order.setUid(1);
//        order.setUsername("测试用户");
//        order.setPid(pid);
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//        order.setNumber(1);
//
//
//
//        orderService.createOrder(order);
//        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
//        return order;
//    }


//    //下单
//    @RequestMapping("/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("{}号商品下单请求",pid);
//        //微服务之间调用：RestTemplate（http）
//        //q
//        //1:服务提供者地址变化，不得不修改服务调用者代码
//        //2：服务提供者做了集群，无法实现负载均衡
//        //3:微服务多了，如何管理？
//        //a
//        //服务治理：注册中心
//        // zookeeper（Apache Hadoop的子项目）
//        // eureka（springcloud netfix 已经闭源）
//        // consul （go）
//        // nacos （springcloud alibaba组件之一） 服务的注册发现和服务配置 nacos=eureka+config
//        Product product = restTemplate.getForObject("http://localhost:8081/product/info/"+pid,Product.class);
//        log.info("查询到{}号商品信息，内容是{}",pid, JSON.toJSONString(product));
//        //下单
//
//        Order order = new Order(){{
//            setUid(1);
//            setUsername("测试用户");
//            setPid(pid);
//            setPname(product.getPname());
//            setPprice(product.getPprice());
//            setNumber(1);
//        }};
//
//        orderService.createOrder(order);
//        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
//        return order;
//    }

}
