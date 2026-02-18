package com.edureka.order.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@Component
public class ProductClient {

    private final WebClient.Builder builder;

    public ProductClient(WebClient.Builder builder) {
        this.builder = builder;
    }

    @CircuitBreaker(name = "productServiceCB", fallbackMethod = "fallbackProduct")
    public Boolean isCategoryAvailable(String category) {

    	return builder.build()
                .get()
                .uri("http://PRODUCT-SERVICE/api/products/exists/{category}", category)
                .retrieve()
                .onStatus(
                        status -> status.is5xxServerError(),
                        response -> Mono.error(new RuntimeException("Product Service Down"))
                )
                .bodyToMono(Boolean.class)
                .block();
    }

    public Boolean fallbackProduct(Throwable ex) {
        System.out.println("Circuit breaker activated: " + ex.getMessage());
        System.out.println("Reason: " + ex.getClass().getSimpleName());
        return false;
    }


}
