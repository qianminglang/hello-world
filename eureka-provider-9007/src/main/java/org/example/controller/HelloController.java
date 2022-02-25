package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName HelloController
 *
 * @author qml
 * Date 2022/2/24 17:26
 * Version 1.0
 **/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(String name) {
        return "hello 9007" + name + " !";
    }
}