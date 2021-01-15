package ru.otus.spring.homework15.services;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.homework15.domain.OrderInternetShop;

import java.util.List;

@MessagingGateway
public interface OrderProcess {

    @Gateway(requestChannel = "inChannel", replyChannel = "outChannel")
    List<OrderInternetShop> process(List<OrderInternetShop> order);
}
