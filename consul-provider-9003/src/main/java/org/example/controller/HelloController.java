package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName HelloController
 *
 * @author qml
 * Date 2022/2/24 15:37
 * Version 1.0
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello consul 9003";
    }
}