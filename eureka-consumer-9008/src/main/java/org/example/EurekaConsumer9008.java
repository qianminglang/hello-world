package org.example;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
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

    /**
     * 配置负载均衡
     *
     * @param
     * @return org.springframework.web.client.RestTemplate
     * @author qml
     * @date 2022/2/25 9:56
     **/
    @Bean
    @LoadBalanced
    RestTemplate loadBalancer() {
        return new RestTemplate();
    }

    /**
     * 配置负载均衡的策略
     * 此处配置随机策略
     *
     * @param
     * @return com.netflix.loadbalancer.IRule
     * @author qml
     * @date 2022/2/25 9:57
     **/
    @Bean
    IRule iRule() {
        return new RoundRobinRule();
    }
}
