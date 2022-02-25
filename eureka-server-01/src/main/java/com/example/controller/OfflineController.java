package com.example.controller;

import com.example.util.R;
import com.netflix.discovery.DiscoveryManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName OfflineController
 *
 * @author qml
 * Date 2022/2/24 9:49
 * Version 1.0
 **/

@RestController
@RequestMapping("/service")
public class OfflineController {
    @PostMapping("/offline")
    @ResponseBody
    public R offline(){
        DiscoveryManager.getInstance().shutdownComponent();
        return R.success();
    }

}