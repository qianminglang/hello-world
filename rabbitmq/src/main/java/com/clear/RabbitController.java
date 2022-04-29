package com.clear;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName RabbitController
 *
 * @author qml
 * Date 2022-4-29 17:54
 * Version 1.0
 **/
@RestController
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @RequestMapping(@PathVariable )
}