package ru.otus.spring.homework10.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "books-service-for-feign",
        fallback = FallBackFeign.class)
public interface MyFeignClient {

    @GetMapping("/api/updateStatus")
    int getNewStatus();
}
