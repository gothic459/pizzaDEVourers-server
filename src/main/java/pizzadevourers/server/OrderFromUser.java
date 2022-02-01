package pizzadevourers.server;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public class OrderFromUser {
    private String user_id;
    private List<OrderedProduct> ordered_products;

    public OrderFromUser(String user_id, List<OrderedProduct> ordered_products) {
        this.user_id = user_id;
        this.ordered_products = ordered_products;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
                "user_id='" + user_id + '\'' +
                ", ordered_products=" + ordered_products.toString() +
                '}';
    }
}

