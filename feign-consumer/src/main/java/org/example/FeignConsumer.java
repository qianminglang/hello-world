package org.example;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableFeignClients
public class FeignConsumer {
    public static void main(String[] args) {
        SpringApplication.run(FeignConsumer.class, args);
    }

    @Bean
    Logger.Level level() {
        return Logger.Level.FULL;
    }

//    @Bean
//    public Retryer feignRetryer() {
//        Retryer.Default retryer = new Retryer.Default();
//        return retryer;
//    }
}
