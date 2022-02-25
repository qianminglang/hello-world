package org.example.service;

import com.example.service.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * ClassName FeignService
 *
 * @author qml
 * Date 2022/2/25 10:58
 * Version 1.0
 **/
@FeignClient("eureka-provider")
public interface FeignService extends HelloService {
}