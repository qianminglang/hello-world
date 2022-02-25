package org.example.controller;

import com.example.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName UseHelloController
 *
 * @author qml
 * Date 2022/2/24 17:36
 * Version 1.0
 **/
@RestController
public class UseHelloController {
//    @Autowired
//    DiscoveryClient discoveryClient;

//    @Autowired
//    LoadBalancerClient loadBalancerClient;

    @Autowired
    @Qualifier("loadBalancer")
    RestTemplate loadBalancer;


    @RequestMapping("/hello")
    public R hello(String name) {
        String result = loadBalancer.getForObject("http://eureka-provider/hello", String.class);
        return R.success(result);
    }
}