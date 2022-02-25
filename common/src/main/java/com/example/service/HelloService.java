package com.example.service;

import com.example.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName helloService
 *
 * @author qml
 * Date 2022/2/25 10:28
 * Version 1.0
 **/
public interface HelloService {
    @PostMapping("/hello")
    String hello(@RequestBody User user);

    @DeleteMapping("/user/{id}")
    void deleteUserById(@PathVariable("id") Long id);

    @GetMapping("/user")
    User getUserByName(@RequestParam("name") String name);

    @PostMapping("/user")
    User addUser(@RequestBody User user);

    @PutMapping("/user")
    void updateUserById(@RequestHeader("name") String name, @RequestHeader("id") Long id);
}