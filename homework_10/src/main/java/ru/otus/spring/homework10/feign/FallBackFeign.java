package ru.otus.spring.homework10.feign;

import org.springframework.stereotype.Component;

@Component
public class FallBackFeign implements MyFeignClient {

    @Override
    public int getNewStatus() {
        int newStatus = 100;
        return newStatus;
    }

}
