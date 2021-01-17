package ru.otus.spring.homework15.domain;

public class OrderInternetShop {

    private final Integer numberOrder;
    private final String product;
    private final String countryProduct;
    private final double price;
    private final String status;


    public OrderInternetShop(String product, Integer numberOrder, String countryProduct, double price, String status) {
        this.product = product;
        this.numberOrder = numberOrder;
        this.countryProduct = countryProduct;
        this.price = price;
        this.status = status;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public Integer getNumberOrder() {
        return numberOrder;
    }

    public String getCountryProduct() {
        return countryProduct;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "OrderInternetShop{" +
                "numberOrder=" + numberOrder +
                ", product='" + product + '\'' +
                ", countryProduct='" + countryProduct + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
