package org.example.controller;

import com.example.entity.User;
import org.example.service.FeignService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName FeignConsumerController
 *
 * @author qml
 * Date 2022/2/25 10:37
 * Version 1.0
 **/
@RestController
public class FeignConsumerController {
    @Resource
    FeignService feignService;

    @PostMapping("/hello")
    public String hello(@RequestBody User user) {
        return feignService.hello(user);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return feignService.addUser(user);
    }

}