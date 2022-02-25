package org.example.util;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;

import java.time.Duration;
import java.util.Date;

/**
 * ClassName Test
 *
 * @author qml
 * Date 2022/2/25 16:46
 * Version 1.0
 **/

public class Test {
    @org.junit.Test
    public void CircuitBreakConfig() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.ofDefaults();
        System.out.println(circuitBreakerConfig);
    }

    @org.junit.Test
    public void CircuitBreakConfigSelf() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .ringBufferSizeInHalfOpenState(2)
                .ringBufferSizeInClosedState(2)
                .build();
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        CircuitBreaker circuitBreaker2 = circuitBreakerRegistry.circuitBreaker("otherName");
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("uniqueName", circuitBreakerConfig);
    }

    @org.junit.Test
    public void CircuitBreaker() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");
        CheckedFunction0<String> decoratedSupplier = CircuitBreaker
                .decorateCheckedSupplier(circuitBreaker, () -> "This can be any method which returns: 'Hello");
        Try<String> result = Try.of(decoratedSupplier)
                .map(value -> value + " world'");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    @org.junit.Test
    public void diffCircuitBreaker() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");
        CircuitBreaker anotherCircuitBreaker = CircuitBreaker.ofDefaults("anotherTestName");
        CheckedFunction0<String> decoratedSupplier = CircuitBreaker
                .decorateCheckedSupplier(circuitBreaker, () -> "Hello");
        CheckedFunction1<String, String> decoratedFunction = CircuitBreaker
                .decorateCheckedFunction(anotherCircuitBreaker, (input) -> input + " world");
        Try<String> result = Try.of(decoratedSupplier)
                .mapTry(decoratedFunction::apply);
        System.out.println(result.isSuccess());
        System.out.println(result.get());

    }

    @org.junit.Test
    public void openCircuitBreaker() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .ringBufferSizeInClosedState(2)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker.of("testName", circuitBreakerConfig);
        circuitBreaker.onError(0, new RuntimeException());
        System.out.println(circuitBreaker.getState());
        circuitBreaker.onError(0, new RuntimeException());
        System.out.println(circuitBreaker.getState());
        Try<String> result = Try.of(CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "Hello"))
                .map(value -> value + " world");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    @org.junit.Test
    public void jjCircuitBreaker() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");
        CheckedFunction0<String> checkedSupplier = CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> {
            throw new RuntimeException("BAM!");
        });
        Try<String> result = Try.of(checkedSupplier)
                .recover(throwable -> "Hello Recovery");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    @org.junit.Test
    public void jtCircuitBreaker() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("test");
        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
        // 获取故障率
        float failureRate = metrics.getFailureRate();
        // 获取调用失败次数
        int failedCalls = metrics.getNumberOfFailedCalls();
        System.out.println(String.format("获取故障率:%s", failureRate));
        System.out.println(String.format("获取调用失败次数:%s", failedCalls));
    }

    @org.junit.Test
    public void rateLimiter() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(1000))
                .limitForPeriod(2)
                .timeoutDuration(Duration.ofMillis(1000))
                .build();
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);
        RateLimiter rateLimiterWithDefaultConfig = rateLimiterRegistry.rateLimiter("backend");
        RateLimiter rateLimiterWithCustomConfig = rateLimiterRegistry.rateLimiter("backend#2", config);
        RateLimiter rateLimiter = RateLimiter.of("NASDAQ :-)", config);

        rateLimiter.getEventPublisher()
                .onSuccess(event -> {
                    System.out.println(new Date()+">>>"+event.getEventType()+">>>"+event.getCreationTime());
                })
                .onFailure(event -> {
                    System.out.println(new Date()+">>>"+event.getEventType()+">>>"+event.getCreationTime());
                });



        //调用
        CheckedRunnable restrictedCall = RateLimiter
                .decorateCheckedRunnable(rateLimiter, () -> {
                    System.out.println(new Date());
                });
        Try.run(restrictedCall)
                .andThenTry(restrictedCall)
                .andThenTry(restrictedCall)
                .andThenTry(restrictedCall)
                .onFailure(throwable -> System.out.println(throwable.getMessage()));

    }

    @org.junit.Test
    public void bulkhead() {
        BulkheadRegistry bulkheadRegistry = BulkheadRegistry.ofDefaults();


        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(150)
                .maxWaitTime(100)
                .build();
        BulkheadRegistry registry = BulkheadRegistry.of(config);
        Bulkhead bulkhead1 = registry.bulkhead("foo");
        BulkheadConfig custom = BulkheadConfig.custom()
                .maxWaitTime(0)
                .build();
        Bulkhead bulkhead2 = registry.bulkhead("bar", custom);

    }
}