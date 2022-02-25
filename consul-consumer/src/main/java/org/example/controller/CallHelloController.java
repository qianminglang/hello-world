package org.example.controller;

import com.example.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * ClassName CallHelloController
 *
 * @author qml
 * Date 2022/2/24 16:28
 * Version 1.0
 **/
@RestController
public class CallHelloController {
    @Autowired
    private LoadBalancerClient loadBalancer;

    @RequestMapping("/call")
    private R call() {
        ServiceInstance serviceInstance = loadBalancer.choose("consule-provider");
        URI uri = serviceInstance.getUri();
        String result = new RestTemplate().getForObject(uri + "/hello", String.class);
        return R.success(result);
    }
}