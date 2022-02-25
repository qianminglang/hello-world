package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 */
@SpringBootApplication
public class EurekaConsumer9008 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumer9008.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate loadBalancer(){
        return new RestTemplate();
    }
}
