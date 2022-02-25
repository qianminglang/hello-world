package org.example.controller;

import com.example.entity.User;
import com.example.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @PostMapping("/hello")
    public R hello(@RequestBody User user) {
        ResponseEntity<String> stringResponseEntity = loadBalancer.postForEntity("http://eureka-provider/hello", user, String.class);
        return R.success(stringResponseEntity.getBody());
    }
}