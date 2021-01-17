package ru.otus.spring.homework15.services;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework15.domain.OrderInternetShop;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServices {

    public static List<OrderInternetShop> generateOrder() {
        List<OrderInternetShop> listOrders = new ArrayList<>();

        String[] products = {"Kolonki", "Telefon", "Utyug", "Tumbochka", "Notebook", "Sapogi", "Kurtka"};
        Double[] prices = {105.00, 51.00, 70.00, 477.00, 777.00};
        String[] countries = {"Russia", "Canada"};

        for (int i = 0; i < RandomUtils.nextInt(1, 5); ++i) {
            String nameProduct = products[RandomUtils.nextInt(0, products.length)];
            String nameCountry = countries[RandomUtils.nextInt(0, countries.length)];
            Double price = prices[RandomUtils.nextInt(0, prices.length)];
            listOrders.add(new OrderInternetShop(
                    nameProduct,
                    i,
                    nameCountry,
                    price,
                    "New order"));
        }

        return listOrders;
    }

    public OrderInternetShop addCommission(OrderInternetShop order) {
        double newPrice = 70 + order.getPrice();
        return new OrderInternetShop(
                order.getProduct(),
                order.getNumberOrder(),
                order.getCountryProduct(),
                newPrice,
                order.getStatus());
    }

    public OrderInternetShop registrationOrder(OrderInternetShop order) {
        return new OrderInternetShop(
                order.getProduct(),
                order.getNumberOrder(),
                order.getCountryProduct(),
                order.getPrice(),
                "Registration");
    }

    public OrderInternetShop readyToSend(OrderInternetShop order) {
        return new OrderInternetShop(
                order.getProduct(),
                order.getNumberOrder(),
                order.getCountryProduct(),
                order.getPrice(),
                "Ready to send");
    }
}
