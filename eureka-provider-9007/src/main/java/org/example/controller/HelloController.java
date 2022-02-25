package org.example.controller;

import com.example.entity.User;
import com.example.service.HelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName HelloController
 *
 * @author qml
 * Date 2022/2/24 17:26
 * Version 1.0
 **/
@RestController
public class HelloController implements HelloService {
    @Value("${server.port}")
    Integer serverPort;

    @Override
    public String hello(User user) {
        return "hello " + serverPort + user + " !";
    }

    @Override
    public void deleteUserById(Long id) {

    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public User addUser(User user) {
        user.setPort(serverPort);
        return user;
    }

    @Override
    public void updateUserById(String name, Long id) {

    }
}