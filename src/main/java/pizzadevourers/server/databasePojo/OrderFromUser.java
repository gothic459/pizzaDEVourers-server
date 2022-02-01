package pizzadevourers.server.databasePojo;

import java.util.List;

public class OrderFromUser {
    private String userJWT;
    private List<OrderedProduct> ordered_products;

    public OrderFromUser(String userJWT, List<OrderedProduct> ordered_products) {
        this.userJWT = userJWT;
        this.ordered_products = ordered_products;
    }

    public String get_userJWT() {
        return userJWT;
    }

    public void set_userJWT(String userJWT) {
        this.userJWT = userJWT;
    }

    public List<OrderedProduct> getOrdered_products() {
        return ordered_products;
    }

    public void setOrdered_products(List<OrderedProduct> ordered_products) {
        this.ordered_products = ordered_products;
    }

    @Override
    public String toString() {
        return "OrderFromUser{" +
                "userJWT='" + userJWT + '\'' +
                ", ordered_products=" + ordered_products.toString() +
                '}';
    }
}

